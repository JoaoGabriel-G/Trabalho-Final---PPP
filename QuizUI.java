package quiz.framework;

import java.util.List;

public interface QuizUI {

    public void mostrarMensagem(String mensagem);
    public void mostrarTitulo(String titulo);
    public void exibirQuestao(Questao questao);
    public List<Integer> obterRespostas(Questao questao);
    public String getNome();
}
