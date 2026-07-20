package quiz.aplicacao.programacao;

import quiz.framework.QuestaoStrategy;
import quiz.framework.Questao;
import quiz.framework.TipoQuestao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class FactoryRespostaUnica implements QuestaoStrategy{

    @Override
    public TipoQuestao getTipo() {
        return TipoQuestaoProgramacao.RESPOSTA_UNICA;
    }

    @Override
    public Questao criar(String enunciado, int quantidadeOpcoes, TipoQuestao tipo, Scanner sc) {

        List<String> alternativas = new ArrayList<>();

        for (int i = 1; i <= quantidadeOpcoes; i++) {
            String textoAlternativa = JOptionPane.showInputDialog(null,
                    "Digite o texto para a alternativa " + i + ":",
                    "Configuração das Alternativas",
                    JOptionPane.QUESTION_MESSAGE);

            // Tratamento caso o usuário cancele
            if (textoAlternativa == null || textoAlternativa.trim().isEmpty()) {
                textoAlternativa = "Alternativa " + i;
            }
            alternativas.add(textoAlternativa);
        }

        StringBuilder menuCorreta = new StringBuilder();
        menuCorreta.append("ENUNCIADO: ").append(enunciado).append("\n");
        menuCorreta.append("========================================\n\n");
        menuCorreta.append("Qual é o índice da alternativa correta?\n\n");

        for (int i = 0; i < alternativas.size(); i++) {
            menuCorreta.append("(").append(i + 1).append(") ").append(alternativas.get(i)).append("\n");
        }

        String inputCorreta = JOptionPane.showInputDialog(null,
                menuCorreta.toString(),
                "Definir Resposta Correta",
                JOptionPane.QUESTION_MESSAGE);

        int correta = 1;
        try {
            correta = Integer.parseInt(inputCorreta);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Definida alternativa 1 como correta por padrão.");
        }

        TipoQuestaoProgramacao tipoEspecifico = (TipoQuestaoProgramacao) tipo;

        return new QuestaoRespostaUnica(enunciado, alternativas, tipoEspecifico, correta);
    }
}
