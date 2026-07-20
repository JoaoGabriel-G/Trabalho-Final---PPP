package quiz.aplicacao.geral;

import quiz.framework.FactoryQuestoes;

public class Main {
    public static void main(String[] args) {

        FactoryQuestoes.registrarEstrategia(new FactoryRespostaUnica());
        FactoryQuestoes.registrarEstrategia(new FactoryRespostaUnicaPenalizacao());
        FactoryQuestoes.registrarEstrategia(new FactoryMultiplasCorretas());

        MeuQuiz1_0 quiz = new MeuQuiz1_0("Quiz Geral");

        quiz.adicionarJogador(new ConsoleUI("Jogador 1"));
        quiz.adicionarJogador(new ConsoleUI("Jogador 2"));
        quiz.adicionarJogador(new ConsoleUI("Jogadora 999"));

        quiz.iniciar();
    }
}