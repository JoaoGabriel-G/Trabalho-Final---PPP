package quiz.aplicacao.geral;

import quiz.framework.QuizUI;
import quiz.framework.Questao;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ConsoleUI implements QuizUI{
    private Scanner sc = new Scanner(System.in);
    private String nomeJogador;

    public ConsoleUI(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    @Override
    public void mostrarMensagem(String mensagem){
        System.out.println(mensagem);
    }

    @Override
    public void mostrarTitulo(String titulo){
        System.out.println("=========== " + titulo + " ===========");
    }

    public String getNome(){
        return this.nomeJogador;
    }

    @Override
    public void exibirQuestao(Questao questao){

        System.out.println("\n========================================");
        System.out.println("VEZ DE: " + nomeJogador.toUpperCase());
        System.out.println("========================================");
        System.out.println(questao.getEnunciado());

        System.out.println("\nENUNCIADO: " + questao.getEnunciado() + "\n");
        int i = 1;
        for(String alternativa : questao.getAlternativas()){
            System.out.println("(" + i + ")" + alternativa);
            i++;
        }
    }

    @Override
    public List<Integer> obterRespostas(Questao questao){

        List<Integer> respostas = new ArrayList<>();

        int selecao = -1;

        System.out.println("\nFaça sua seleção, digite e dê enter em um número por vez:");
        System.out.println("Para finalizar a seleceção, digite 0\n");

        while(true){

            selecao = sc.nextInt();

            if(selecao == 0){
                break;
            } else if(selecao < 0 || selecao > questao.getAlternativas().size()){
                System.out.println("Alternativa não reconhecida, digite novamente");
            } else {
                respostas.add(selecao);
                System.out.println("!! Resposta adicionada !!");
            }
        }
        sc.nextLine(); // Limpeza do buffer
        return respostas;
    }
}
