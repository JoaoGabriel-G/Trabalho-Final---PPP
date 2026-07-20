package quiz.aplicacao.programacao;

import quiz.framework.FactoryQuestoes;

public class Main {
    public static void main(String[] args) {

        FactoryQuestoes.registrarEstrategia(new FactoryRespostaUnica());

        MeuQuiz2_0 quiz = new MeuQuiz2_0("Quiz De Programação");

        quiz.adicionarJogador(new SwingUI("Jogador A"));
        quiz.adicionarJogador(new SwingUI("Jogador B"));

        quiz.iniciar();
    }
}