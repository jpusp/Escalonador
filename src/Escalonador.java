import log.LoggerImpl;
import scheduler.ProcessScheduler;
import process.InstructionDecoder;
import data.MockProcessRepositoryImpl;

public class Escalonador {
    private static final int TEMPO_ESPERA = 2;
    private static final int QUANTUM = 3;
    public static void main(String[] args) {
        ProcessScheduler escalonador = new ProcessScheduler(
                //new RepositorioDeProcessosImpl(),
                new MockProcessRepositoryImpl(),
                new InstructionDecoder(TEMPO_ESPERA),
                new LoggerImpl(),
                QUANTUM
        );

        escalonador.start();
    }
}