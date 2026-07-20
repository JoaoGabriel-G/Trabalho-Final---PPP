package quiz.aplicacao.geral;

import quiz.framework.Questao;
import quiz.framework.TipoQuestao;

import java.util.List;

public class QuestaoRespostaUnicaPenalizacao implements Questao{

    String enunciado;
    List<String> alternativas;
    TipoQuestaoGeral tipo;
    int correta;

    public QuestaoRespostaUnicaPenalizacao(String enunciado, List<String> alternativas, TipoQuestao tipo, int correta) {
        this.enunciado = enunciado;
        this.tipo = (TipoQuestaoGeral) tipo;
        this.alternativas = alternativas;
        this.correta = correta;
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
    public double verificarRespostas(List<Integer> resposta){

        if(resposta == null || resposta.size() > 1){
            System.out.println("Mais de uma resposta escolhida, pontuação penalizada!");
            return -1.0;
        }

        if(resposta.contains(correta)){
            return 1.0;
        }
        return -1.0;   // Errar remove um ponto da pontuação
    }
}
