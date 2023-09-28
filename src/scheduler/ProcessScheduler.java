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
    private int executedProcessCount = 0;

    public ProcessScheduler(
            ProcessRepository repositorio,
            InstructionDecoder instructionDecoder,
            Logger logger,
            int quantum
    ) {
        this.instructionDecoder = instructionDecoder;
        this.logger = logger;
        this.quantum = quantum;
        readyProcesses = new ArrayDeque<>();
        processTable = repositorio.loadProcesses();
        if (processTable != null) {
            processTable.forEach (processo -> readyProcesses.add(processo));
        }
        blockedProcesses = new ArrayDeque<>();
    }

    public void start() {
        while (!(readyProcesses.isEmpty() && blockedProcesses.isEmpty())) {
            BCP process = readyProcesses.poll();
            if (process == null) {
                checkBlockedProcesses();
            } else {
                runProcess(process);
                if ((executedProcessCount % 2) == 0) {
                    checkBlockedProcesses();
                }
            }
        }

        logger.log("MEDIA DE TROCAS: ");
        logger.log("MEDIA DE INSTRUCOES: ");
        logger.log("QUANTUM: " + quantum);
    }

    private void runProcess(BCP process) {
        logger.log("Executando " + process.getName());
        process.setState(RUNNING);
        int localQuantum = quantum;
        while (localQuantum > 0 && process.getState() != BLOCKED) {
            String instruction = process.getInstruction();
            if (instruction.equals(EXIT_COMMAND)) {
                readyProcesses.remove(process);
                processTable.remove(process);
                executedProcessCount++;
                logger.log(process.getName() + " terminado. X=" + process.getX() + ". Y=" + process.getY());
                return;
            } else {
                instructionDecoder.decode(process, instruction);

                if (process.getState() == BLOCKED) {
                    logger.log("E/S iniciada em " + process.getName());
                    blockedProcesses.add(process);
                } else {
                    localQuantum--;
                }
            }
        }

        logger.log("Interrompendo " + process.getName() + " após " + (quantum - localQuantum) + " instruções");

        if (process.getState() != BLOCKED) {
            readyProcesses.add(process);
        }

        executedProcessCount++;
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
}
