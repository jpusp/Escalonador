import log.Logger;
import log.LoggerImpl;
import scheduler.ProcessScheduler;
import process.InstructionDecoder;
import data.MockProcessRepositoryImpl;

public class Escalonador {
    private static final int TEMPO_ESPERA = 2;
    private static final int QUANTUM = 3;
    public static void main(String[] args) {
        Logger logger = new LoggerImpl(QUANTUM);
        ProcessScheduler escalonador = new ProcessScheduler(
                //new RepositorioDeProcessosImpl(),
                new MockProcessRepositoryImpl(),
                new InstructionDecoder(TEMPO_ESPERA),
                logger,
                QUANTUM
        );

        escalonador.start();

        logger.log("MEDIA DE TROCAS: " + escalonador.getProcessSwapAverage());
        logger.log("MEDIA DE INSTRUCOES: " + String.format("%.2f", escalonador.getInstructionsAverage()));
        logger.log("QUANTUM: " + QUANTUM);
    }
}