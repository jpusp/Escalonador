import escalonador.Escalonador;
import processo.DecodificadorDeInstrucao;
import repositorio.RepositorioDeProcessosImpl;

public class Main {
    private static final int TEMPO_ESPERA = 2;
    private static final int QUANTUM = 2;
    public static void main(String[] args) {
        Escalonador escalonador = new Escalonador(
                new RepositorioDeProcessosImpl(),
                new DecodificadorDeInstrucao(TEMPO_ESPERA),
                QUANTUM
        );

        escalonador.inicia();
    }
}