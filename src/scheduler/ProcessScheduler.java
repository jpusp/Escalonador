package scheduler;

import log.Logger;
import process.BCP;
import process.InstructionDecoder;
import data.ProcessRepository;

import java.util.*;

import static process.ProcessState.BLOCKED;
import static process.ProcessState.RUNNING;

public class ProcessScheduler {
    private static final String EXIT_COMMAND = "SAIDA";
    private List<BCP> processTable;
    private final InstructionDecoder instructionDecoder;
    private final Logger logger;
    private final int quantum;
    private Queue<BCP> readyProcesses;
    private Queue<BCP> blockedProcesses;
    private int initialProcessCount;
    private int processSwapCount = 0;
    private int totalExecutedInstructionsCount = 0;

    public ProcessScheduler(
            ProcessRepository processRepository,
            InstructionDecoder instructionDecoder,
            Logger logger,
            int quantum
    ) {
        this.instructionDecoder = instructionDecoder;
        this.logger = logger;
        this.quantum = quantum;
        readyProcesses = new ArrayDeque<>();
        processTable = Objects.requireNonNullElse(processRepository.loadProcesses(), new ArrayList<>());
        initialProcessCount = processTable.size();
        processTable.forEach (processo -> readyProcesses.add(processo));
        blockedProcesses = new ArrayDeque<>();
    }

    public void start() {
        while (!isEmpty()) {
            if (readyProcesses.isEmpty()) {
                checkBlockedProcesses();
            } else {
                BCP process = readyProcesses.poll();
                runProcess(process);
                if ((processSwapCount % 2) == 0) {
                    checkBlockedProcesses();
                }
            }
        }
    }

    private boolean isEmpty() {
        return readyProcesses.isEmpty() && blockedProcesses.isEmpty();
    }

    private void runProcess(BCP process) {
        logger.logExecution(process);
        process.setState(RUNNING);
        int localQuantum = quantum;
        while (localQuantum > 0 && process.getState() != BLOCKED) {
            String instruction = process.getInstruction();
            if (instruction.equals(EXIT_COMMAND)) {
                processTable.remove(process);
                processSwapCount++;
                logger.logFinish(process);
                return;
            } else {
                instructionDecoder.decode(process, instruction);
                if (process.getState() == BLOCKED) {
                    logger.logES(process);
                    blockedProcesses.add(process);
                }

                localQuantum--;
            }
        }

        processSwapCount++;
        int executedInstructions = quantum - localQuantum;
        totalExecutedInstructionsCount += executedInstructions;
        logger.logInterruption(process, executedInstructions);

        if (process.getState() != BLOCKED) {
            readyProcesses.add(process);
        }
    }

    private void checkBlockedProcesses() {
        List<BCP> processesToBeExcluded = new ArrayList<>();
        blockedProcesses.forEach(process -> {
            process.decBlockWaitTime();
            if (process.getBlockedWaitTime() == 0) {
                readyProcesses.add(process);
                processesToBeExcluded.add(process);
            }
        });

        for (BCP process : processesToBeExcluded) {
            blockedProcesses.remove(process);
        }
    }

    public int getProcessSwapAverage() {
        return processSwapCount / initialProcessCount;
    }

    public double getInstructionsAverage() {
        return totalExecutedInstructionsCount / (double) processSwapCount;
    }
}
