package quiz.aplicacao.geral;

import quiz.framework.QuestaoStrategy;
import quiz.framework.Questao;
import quiz.framework.TipoQuestao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FactoryMultiplasCorretas implements QuestaoStrategy{

    @Override
    public TipoQuestaoGeral getTipo() {
        return TipoQuestaoGeral.MULTIPLAS_CORRETAS;
    }

    public Questao criar(String enunciado, int quantidadeOpcoes, TipoQuestao tipo, Scanner sc){

        List<String> alternativas = new ArrayList<>();

        int i = 1;
        while(quantidadeOpcoes > 0){

            System.out.println("Descrição da alternativa " + i + " :");
            String textoAlternativa = sc.nextLine();

            alternativas.add(textoAlternativa);

            i++;
            quantidadeOpcoes--;
        }

        List<Integer> corretas = new ArrayList<>();

        System.out.println("Digite a quantidade de corretas:");
        int quantidadeCorretas = sc.nextInt();
        sc.nextLine(); //Limpeza do buffer

        int j = 1;
        while (j <= quantidadeCorretas) {
            System.out.println("Digite o índice da " + j + "ª correta:");
            if (sc.hasNextInt()) {
                int correta = sc.nextInt();
                int corretaReal = correta - 1;

                if (corretaReal >= 0 && corretaReal < alternativas.size()) {
                    corretas.add(corretaReal);
                    j++;
                } else {
                    System.out.println("Índice inválido! Digite um número entre 1 e " + alternativas.size());
                }
            }
        }
        sc.nextLine(); // Limpeza do buffer

        return new QuestaoMultiplasCorretas(enunciado, alternativas, tipo, corretas);
    }
}
