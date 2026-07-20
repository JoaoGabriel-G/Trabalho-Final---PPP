package quiz.framework;

import java.util.ArrayList;
import java.util.List;

public abstract class FrameworkQuiz {

    public String titulo;
    public List<Questao> questoes = new ArrayList<>();
    public List<QuizUI> jogadores = new ArrayList<>();


    public FrameworkQuiz(String titulo) {
        this.titulo = titulo;
    }

    // Metodo addObserver
    public void adicionarJogador(QuizUI jogador){  //
        if(jogadores != null){
            this.jogadores.add(jogador);
        }
    }

    // Metodo notifyObserver
    public abstract void notificarJogador(QuizUI jogador);

    public void iniciar(){

        this.questoes = gerarQuestoes();

        // ----------------------------
        // Lógica de andamento do Quiz
        // ----------------------------

    }

    public abstract List<Questao> gerarQuestoes();

    public abstract void finalizar(double pontuacaoAcumulada);

    public abstract double multiplicador();

}
