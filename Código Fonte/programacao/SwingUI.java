package quiz.aplicacao.programacao;

import quiz.framework.*;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

public class SwingUI implements QuizUI{

    private String nomeJogador;
    private JFrame frame;

    public SwingUI(String nomeJogador) {
        this.nomeJogador = nomeJogador;

        this.frame = new JFrame("Quiz Programação - " + this.nomeJogador);
        this.frame.setSize(500,400);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
    }

    @Override
    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this.frame, mensagem, "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarTitulo(String titulo) {
        JOptionPane.showMessageDialog(null, "============= !! " + titulo + " !! =============", "Boas-vindas", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void exibirQuestao(Questao questao) {
        this.frame.setVisible(true);
        this.frame.toFront(); // Traz a janela do jogador da vez para frente
    }

    @Override
    public String getNome(){
        return this.nomeJogador;
    }

    @Override
    public List<Integer> obterRespostas(Questao questao) {

        List<Integer> respostas = new ArrayList<>();

        StringBuilder sb = new StringBuilder(questao.getEnunciado() + "\n\n");
        List<String> alternativas = questao.getAlternativas();

        for (int i = 0; i < alternativas.size(); i++) {
            sb.append("(").append(i + 1).append(") ").append(alternativas.get(i)).append("\n");
        }

        String input = JOptionPane.showInputDialog(this.frame, sb.toString(), "Selecione a alternativa correta", JOptionPane.QUESTION_MESSAGE);

        try {
            int selecao = Integer.parseInt(input);
            respostas.add(selecao);
        } catch (NumberFormatException e) {
            mostrarMensagem("Entrada inválida! Pontuação zerada para esta questão.");
        }

        return respostas;
    }
}
