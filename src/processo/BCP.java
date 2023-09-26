package processo;

import java.util.List;

public class BCP {
    private String nome;
    private EstadoProcesso estadoProcesso;
    private int pc = 0;
    private int x = 0;
    private int y = 0;
    List<String> instrucoes;


    public BCP(String nome, EstadoProcesso estado, List<String> instrucoes) {
        this.nome = nome;
        this.estadoProcesso = estado;
        this.instrucoes = instrucoes;
    }

    public String getNome() {
        return nome;
    }

    public EstadoProcesso getEstadoProcesso() {
        return estadoProcesso;
    }

    public void setEstadoProcesso(EstadoProcesso estadoProcesso) {
        this.estadoProcesso = estadoProcesso;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getInstrucao() {
        String instrucao = instrucoes.get(pc);
        pc++;
        return instrucao;
    }
}