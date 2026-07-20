package quiz.aplicacao.programacao;

import quiz.framework.FrameworkQuiz;
import quiz.framework.Questao;
import quiz.framework.QuizUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class MeuQuiz2_0 extends FrameworkQuiz {

    private Scanner scanner = new Scanner(System.in);

    public MeuQuiz2_0(String titulo) {
        super(titulo);
    }

    // Embora esse metodo seja mais explicito para o Padrao Observer,
    // o metodo iniciar de forma geral apresenta o Padrao "diluido" nele tambem

    public void notificarJogador(QuizUI jogador){
        jogador.mostrarTitulo(this.titulo);
        jogador.mostrarMensagem("=== BEM VINDO " + jogador.getNome() + "!! ===");
    }

    @Override
    public void iniciar() {

        this.questoes = gerarQuestoes();

        int qtdJogadores = jogadores.size();
        double[] pontuacoes = new double[qtdJogadores];
        int[] totalQuestoes = new int[qtdJogadores];
        int[] acertos = new int[qtdJogadores];
        int[] erros = new int[qtdJogadores];

        for (QuizUI jogador : jogadores) {
            notificarJogador(jogador);
        }


        for (Questao questao : questoes) {

            for (int i = 0; i < jogadores.size(); i++) {
                QuizUI jogador = jogadores.get(i);

                jogador.exibirQuestao(questao);

                List<Integer> resposta = jogador.obterRespostas(questao);

                totalQuestoes[i]++;

                double resultadoQuestao = questao.verificarRespostas(resposta);

                if (resultadoQuestao == 1.0) {
                    jogador.mostrarMensagem("\n-> Resposta CORRETA! Você ganhou " + (1 * multiplicador()) + " ponto");
                    acertos[i]++;
                } else {
                    jogador.mostrarMensagem("\n-> Resposta INCORRETA!");
                    erros[i]++;
                }

                pontuacoes[i] = pontuacoes[i] + resultadoQuestao;
            }
        }

        for (int i = 0; i < jogadores.size(); i++) {
            QuizUI jogador = jogadores.get(i);

            StringBuilder relatorio = new StringBuilder();
            relatorio.append("             FIM DAS PERGUNTAS             \n");
            relatorio.append("========================================\n\n");
            relatorio.append("Total de questões respondidas: ").append(totalQuestoes[i]).append("\n");
            relatorio.append("Questões corretas: ").append(acertos[i]).append("\n");
            relatorio.append("Questões incorretas: ").append(erros[i]).append("\n");

            jogador.mostrarMensagem(relatorio.toString());

            finalizar(pontuacoes[i]);
        }
    }

    @Override
    public List<Questao> gerarQuestoes() {
        List<Questao> questoesGeradas = new ArrayList<>();

        // Questão 1
        List<String> alt1 = new ArrayList<>();
        alt1.add("Mecanismo que permite a uma classe herdar atributos e métodos de outra.");
        alt1.add("A capacidade de um objeto assumir várias formas.");
        alt1.add("O encapsulamento de variáveis privadas.");
        alt1.add("Acoplamento menor entre classes.");
        questoesGeradas.add(new QuestaoRespostaUnica(
                "O que define o conceito de Herança em Programação Orientada a Objetos?",
                alt1, TipoQuestaoProgramacao.RESPOSTA_UNICA, 1));

        // Questão 2
        List<String> alt2 = new ArrayList<>();
        alt2.add("String");
        alt2.add("int");
        alt2.add("boolean");
        alt2.add("double");
        questoesGeradas.add(new QuestaoRespostaUnica(
                "Qual dos seguintes tipos em Java NÃO é um tipo primitivo?",
                alt2, TipoQuestaoProgramacao.RESPOSTA_UNICA, 1));

        // Questão 3
        List<String> alt3 = new ArrayList<>();
        alt3.add("Finalizar a execução do programa imediatamente.");
        alt3.add("Interromper a execução de um loop (como for ou while).");
        alt3.add("Pular para a próxima iteração do loop sem sair dele.");
        alt3.add("Retornar um valor de dentro de uma função.");
        questoesGeradas.add(new QuestaoRespostaUnica(
                "Para que serve a palavra-chave 'break' dentro de um laço de repetição?",
                alt3, TipoQuestaoProgramacao.RESPOSTA_UNICA, 2));

        // Questão 4
        List<String> alt4 = new ArrayList<>();
        alt4.add("LIFO (Last In, First Out)");
        alt4.add("FIFO (First In, First Out)");
        alt4.add("LILO (Last In, Last Out)");
        alt4.add("Acesso totalmente aleatório");
        questoesGeradas.add(new QuestaoRespostaUnica(
                "Qual é o comportamento padrão de uma estrutura de dados do tipo Pilha (Stack)?",
                alt4, TipoQuestaoProgramacao.RESPOSTA_UNICA, 1));

        // Questão 5
        List<String> alt5 = new ArrayList<>();
        alt5.add("Garantir que o código rode mais rápido.");
        alt5.add("Capturar e tratar erros em tempo de execução para evitar o travamento do sistema.");
        alt5.add("Alocar memória dinamicamente para novos objetos.");
        alt5.add("Compilar o código em segundo plano.");
        questoesGeradas.add(new QuestaoRespostaUnica(
                "Qual é o principal objetivo do bloco try-catch em Java?",
                alt5, TipoQuestaoProgramacao.RESPOSTA_UNICA, 2));

        this.questoes.addAll(questoesGeradas);
        return questoesGeradas;
    }

    @Override
    public void finalizar(double pontuacaoFinal) {
        pontuacaoFinal = pontuacaoFinal * multiplicador();
        JOptionPane.showMessageDialog(null,
                "OBRIGADO POR JOGAR!\n\nPontuação total obtida: " + pontuacaoFinal, "Fim do Jogo", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public double multiplicador(){
        // Nesse caso quero que a pontuação final seja divida por 2
        return 0.5;
    }
}
