package quiz.aplicacao.geral;

import quiz.framework.QuestaoStrategy;
import quiz.framework.Questao;
import quiz.framework.TipoQuestao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FactoryRespostaUnicaPenalizacao implements QuestaoStrategy{

    @Override
    public TipoQuestao getTipo() {
        return TipoQuestaoGeral.RESPOSTA_UNICA_PENALIZACAO;
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

        System.out.println("Digite o índice da alternativa correta:");
        int correta = sc.nextInt();

        sc.nextLine();  //Limpeza do Buffer

        return new QuestaoRespostaUnicaPenalizacao(enunciado, alternativas, tipo, correta);
    }
}
