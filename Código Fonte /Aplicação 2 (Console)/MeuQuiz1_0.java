package quiz.aplicacao.geral;

import quiz.framework.FrameworkQuiz;
import quiz.framework.Questao;
import quiz.framework.QuestaoStrategy;
import quiz.framework.QuizUI;
import quiz.framework.FactoryQuestoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeuQuiz1_0 extends FrameworkQuiz{

    private Scanner scanner = new Scanner(System.in);

    public MeuQuiz1_0(String titulo){
        super(titulo);
    }

    // Embora esse metodo seja mais explicito para o Padrao Observer,
    // o metodo iniciar de forma geral apresenta o Padrao "diluido" nele tambem

    public void notificarJogador(QuizUI jogador){
        jogador.mostrarTitulo(this.titulo);
        jogador.mostrarMensagem("=== BEM VINDO! " + jogador.getNome() + "!! ===");
    }

    @Override
    public void iniciar() {

        this.questoes = gerarQuestoes();

        int qtdJogadores = jogadores.size();
        double[] pontuacoes = new double[qtdJogadores];
        int[] totalQuestoes = new int[qtdJogadores];
        int[] acertos = new int[qtdJogadores];
        int[] acertosParciais  = new int[qtdJogadores];
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

                double pontosDaQuestao = questao.verificarRespostas(resposta);

                if (pontosDaQuestao == 1.0) {
                    jogador.mostrarMensagem("\n-> Resposta CORRETA! Você ganhou " + (1 * multiplicador()) + " ponto");
                    acertos[i]++;
                } else if (pontosDaQuestao > 0.0 && pontosDaQuestao < 1.0) {
                    jogador.mostrarMensagem("\n-> Resposta PARCIALMENTE CORRETA! Você ganhou " + (1 * multiplicador()) + " ponto");
                    acertosParciais[i]++;
                } else {
                    jogador.mostrarMensagem("\n-> Resposta INCORRETA!");
                    erros[i]++;
                }

                pontuacoes[i] = pontuacoes[i] + pontosDaQuestao ;
            }
        }

        for (int i = 0; i < jogadores.size(); i++) {
            QuizUI jogador = jogadores.get(i);

            System.out.println("\n========================================");
            System.out.println("     FIM DAS PERGUNTAS - JOGADOR " + jogador.getNome());
            System.out.println("========================================");
            System.out.println("Total de questões respondidas: " + totalQuestoes[i]);
            System.out.println("Questões corretas: " + acertos[i]);
            System.out.println("Questões parcialmente corretas: " + acertosParciais[i] );
            System.out.println("Questões incorretas: " + erros[i]);
            System.out.println("----------------------------------------");
            finalizar(pontuacoes[i]);
        }
    }

    @Override
    public List<Questao> gerarQuestoes(){

        List<Questao> questoesGeradas = new ArrayList<>();

        System.out.print("Digite quantas questões o Quiz tera: ");

        int quantas = scanner.nextInt();
        scanner.nextLine(); // Limpeza do buffer

        List<QuestaoStrategy> estrategiasDisponiveis = FactoryQuestoes.getEstrategias();

        if (estrategiasDisponiveis.isEmpty()) {
            System.out.println("Aviso: Nenhum tipo de questão registrado!");
            return questoesGeradas;
        }

        int i = 1;
        while (i <= quantas) {
            System.out.println("\n--- Configurando a Questão " + i + " de " + quantas + " ---");
            System.out.println("Selecione o tipo da questão:");

            for (int j = 0; j < estrategiasDisponiveis.size(); j++) {
                System.out.println("(" + (j + 1) + ") - " + estrategiasDisponiveis.get(j).getTipo());
            }

            System.out.print("Sua opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpeza do buffer

            if (opcao > 0 && opcao <= estrategiasDisponiveis.size()) {

                QuestaoStrategy estrategiaEscolhida = estrategiasDisponiveis.get(opcao - 1);

                System.out.print("Digite o enunciado da questão: ");
                String enunciado = scanner.nextLine();

                System.out.print("Digite a quantidade de alternativas: ");
                int qtdAlternativas = scanner.nextInt();
                scanner.nextLine(); // Limpeza do buffer

                Questao novaQuestao = estrategiaEscolhida.criar(enunciado, qtdAlternativas, estrategiaEscolhida.getTipo(), scanner);

                questoesGeradas.add(novaQuestao);
                this.questoes.add(novaQuestao);

                i++;
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }

        System.out.println("\n--- QUESTÕES GERADAS COM SUCESSO! ---\n");
        return questoesGeradas;
    }

    @Override
    public void finalizar(double pontuacaoFinal) {

        pontuacaoFinal = pontuacaoFinal * multiplicador();

        System.out.println("\n==================================");
        System.out.println("   OBRIGADO POR JOGAR!   ");
        System.out.println("\nPontuação total obtida: " + pontuacaoFinal);
        System.out.println("==================================");
    }

    @Override
    public double multiplicador(){
        // Nesse caso quero que a pontuação final seja multiplicada por 10
        return 10.0;
    }
}
