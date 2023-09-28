package escalonador;

import processo.BCP;
import processo.DecodificadorDeInstrucao;
import repositorio.RepositorioDeProcessos;

import java.util.*;

import static processo.EstadoProcesso.BLOQUEADO;
import static processo.EstadoProcesso.EXECUTANDO;

public class Escalonador {
    private static final String SAIDA = "SAIDA";
    private List<BCP> tabelaDeProcessos;
    private final DecodificadorDeInstrucao decodificador;
    private final int quantum;
    private Queue<BCP> processosProntos;
    private Queue<BCP> processosBloqueados;
    private int numProcessosExecutados = 0;

    public Escalonador(
            RepositorioDeProcessos repositorio,
            DecodificadorDeInstrucao decodificador,
            int quantum
    ) {
        this.decodificador = decodificador;
        this.quantum = quantum;
        processosProntos = new ArrayDeque<>();
        tabelaDeProcessos = repositorio.getProcessos();
        if (tabelaDeProcessos != null) {
            tabelaDeProcessos.forEach (processo -> processosProntos.add(processo));
        }
        processosBloqueados = new ArrayDeque<>();
    }

    public void inicia() {
        while (!(processosProntos.isEmpty() && processosBloqueados.isEmpty())) {
            BCP processo = processosProntos.poll();
            if (processo == null) {
                verificaBloqueados();
            } else {
                rodaProcesso(processo);
                if ((numProcessosExecutados % 2) == 0) {
                    verificaBloqueados();
                }
            }
        }
    }

    private void rodaProcesso(BCP processo) {
        processo.setEstadoProcesso(EXECUTANDO);
        int localQuantum = quantum;
        while (localQuantum > 0) {
            String instrucao = processo.getInstrucao();
            if (instrucao.equals(SAIDA)) {
                processosProntos.remove(processo);
                tabelaDeProcessos.remove(processo);
                localQuantum = 0;
                return;
            } else {
                decodificador.decodifica(processo, instrucao);

                if (processo.getEstadoProcesso() == BLOQUEADO) {
                    processosBloqueados.add(processo);
                    localQuantum = 0;
                } else {
                    localQuantum--;
                }
            }
        }

        if (processo.getEstadoProcesso() != BLOQUEADO) {
            processosProntos.add(processo);
        }

        numProcessosExecutados++;
    }

    private void verificaBloqueados() {
        List<BCP> processosParaExclusao = new ArrayList<>();
        processosBloqueados.forEach(processo -> {
            processo.decTempoDeEspera();
            if (processo.getTempoDeEspera() == 0) {
                processosProntos.add(processo);
                processosParaExclusao.add(processo);
            }
        });

        for (BCP processo : processosParaExclusao) {
            processosBloqueados.remove(processo);
        }
    }
}
