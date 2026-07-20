package quiz.framework;

import java.util.Scanner;

public interface QuestaoStrategy {

    public TipoQuestao getTipo();
    public Questao criar(String enunciado, int quantidadeOpcoes, TipoQuestao tipo, Scanner sc);
}
