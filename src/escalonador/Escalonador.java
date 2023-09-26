package escalonador;

import processo.BCP;
import processo.EstadoProcesso;

import java.util.ArrayList;
import java.util.List;

import static processo.EstadoProcesso.*;

public class Escalonador {
    private List<BCP> processos;
    private List<BCP> processosBloqueados = new ArrayList<>();

    public Escalonador(ArrayList<BCP> processos) {
        this.processos = processos;
    }

    public void addProcesso(BCP processo) {
        if (processo.getEstadoProcesso() == BLOQUEADO) {
            processosBloqueados.add(processo);
        } else {
            processos.add(processo);
        }
    }
}
