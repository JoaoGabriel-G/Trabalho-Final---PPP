package quiz.aplicacao.geral;

import quiz.framework.TipoQuestao;

public enum TipoQuestaoGeral implements TipoQuestao{

    RESPOSTA_UNICA("Resposta Unica"),
    RESPOSTA_UNICA_PENALIZACAO("Resposta Unica Penalizacao"),
    MULTIPLAS_CORRETAS("Multiplas Corretas");

    private final String nome;

    private TipoQuestaoGeral(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome(){
        return this.nome;
    }
}
