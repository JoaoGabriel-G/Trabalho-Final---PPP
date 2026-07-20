package quiz.aplicacao.programacao;

import quiz.framework.TipoQuestao;

public enum TipoQuestaoProgramacao implements TipoQuestao{

    RESPOSTA_UNICA("Resposta Unica");

    private final String nome;

    private TipoQuestaoProgramacao(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome(){
        return this.nome;
    }
}
