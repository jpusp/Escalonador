package processo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecodificadorDeInstrucao {
    private final int tempoDeEspera;
    private Pattern atribuicaoPattern = Pattern.compile("(X|Y)=([0-9]+)");
    private Pattern ESPattern = Pattern.compile("^E\\/S$");

    public DecodificadorDeInstrucao(int tempoDeEspera) {
        this.tempoDeEspera = tempoDeEspera;
    }

    public void decodifica(BCP processo, String instrucao) {
        Matcher matcherAtribuicao = atribuicaoPattern.matcher(instrucao);
        Matcher matcherES = ESPattern.matcher(instrucao);

        if (matcherAtribuicao.matches()) {
            fazAtribuicao(processo, matcherAtribuicao);
        } else if (matcherES.matches()) {
            processo.setTempoDeEspera(tempoDeEspera);
            processo.setEstadoProcesso(EstadoProcesso.BLOQUEADO);
        } else {

        }
    }

    private void fazAtribuicao(BCP processo, Matcher matcher) {
        String registro = matcher.group(1);
        int valor = Integer.parseInt(matcher.group(2));

        if ("X".equals(registro)) {
            processo.setX(valor);
        } else {
            processo.setY(valor);
        }
        processo.setEstadoProcesso(EstadoProcesso.PRONTO);
    }
}
