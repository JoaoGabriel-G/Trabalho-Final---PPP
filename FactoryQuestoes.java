package quiz.framework;

import java.util.ArrayList;
import java.util.List;

public abstract class FactoryQuestoes {

    public static final List<QuestaoStrategy> estrategias = new ArrayList<>();

    public static void registrarEstrategia(QuestaoStrategy estrategia) {
        estrategias.add(estrategia);
    }

    public static List<QuestaoStrategy> getEstrategias() {
        return estrategias;
    }
}



