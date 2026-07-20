package quiz.aplicacao.programacao;

import quiz.framework.*;

import java.util.List;

public class QuestaoRespostaUnica implements Questao{

    String enunciado;
    List<String> alternativas;
    TipoQuestao tipo;
    int correta;

    public QuestaoRespostaUnica(String enunciado, List<String> alternativas, TipoQuestao tipo, int correta) {
        this.enunciado = enunciado;
        this.tipo = (TipoQuestaoProgramacao) tipo;
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
            System.out.println("Mais de uma resposta escolhida, pontuação da questão anulada!");
            return 0.0;
        }

        if(resposta.contains(correta)){
            return 1.0;
        }
        return 0.0;
    }
}
