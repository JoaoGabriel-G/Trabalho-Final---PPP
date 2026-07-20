package quiz.aplicacao.geral;

import quiz.framework.Questao;
import quiz.framework.TipoQuestao;

import java.util.List;

public class QuestaoMultiplasCorretas implements Questao{

    String enunciado;
    List<String> alternativas;
    TipoQuestaoGeral tipo;
    List<Integer> corretas;

    public QuestaoMultiplasCorretas(String enunciado, List<String> alternativas, TipoQuestao tipo, List<Integer> corretas) {
        this.enunciado = enunciado;
        this.tipo = (TipoQuestaoGeral) tipo;
        this.alternativas = alternativas;
        this.corretas = corretas;
    }

    public String getEnunciado(){
        return this.enunciado;
    }
    public TipoQuestao getTipo(){
        return this.tipo;
    }
    public List<String> getAlternativas() {
        return this.alternativas;
    }

    @Override
    public double verificarRespostas(List<Integer> respostas){

        if (respostas == null || respostas.isEmpty() || corretas.isEmpty()) {
            return 0.0;
        }

        double acertos = 0.0;

        for (Integer resp : respostas) {
            int respostaBaseZero = resp - 1;
            if (corretas.contains(respostaBaseZero)) {
                acertos++;
            } else {
                acertos--;  // Uma errada anula uma certa
            }
            if(acertos < 0){
                return 0.0;
            }
        }

        return acertos / (double) corretas.size();
    }
}