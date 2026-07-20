package quiz.framework;

import java.util.List;

public interface Questao {

    public String getEnunciado();
    public TipoQuestao getTipo();
    public List<String> getAlternativas();
    public double verificarRespostas(List<Integer> respostas);

}
