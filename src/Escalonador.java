import data.ProcessRepository;
import data.ProcessRepositoryImpl;
import data.QuantumRepository;
import data.QuantumRepositoryImpl;
import log.Logger;
import log.LoggerImpl;
import scheduler.ProcessScheduler;
import process.InstructionDecoder;

public class Escalonador {
    private static final int WAIT_QUANTUM = 2;
    public static void main(String[] args) {
        QuantumRepository quantumRepository = new QuantumRepositoryImpl();
        int quantum = quantumRepository.loadQuantum();
        Logger logger = new LoggerImpl(quantum);
        ProcessRepository processRepository = new ProcessRepositoryImpl(logger);
        InstructionDecoder decoder = new InstructionDecoder(WAIT_QUANTUM);

        ProcessScheduler escalonador = new ProcessScheduler(processRepository, decoder, logger, quantum);
        escalonador.start();

        logger.log("MEDIA DE TROCAS: " + String.format("%.2f", escalonador.getProcessSwapAverage()));
        logger.log("MEDIA DE INSTRUCOES: " + String.format("%.2f", escalonador.getInstructionsAverage()));
        logger.log("QUANTUM: " + quantum);
    }
}