import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //criando listas
        ArrayList<Aluno> alunos = new ArrayList<>();
        ArrayList<Professor> professores = new ArrayList<>();
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        ArrayList<Matricula> matriculas = new ArrayList<>();

        // Instanciando profs
        Professor p1 = new Professor("Carlos", "111.111.111-11", "carlos@puc.com", "Engenharia");
        Professor p2 = new Professor("Akitodr", "222.222.222-22", "akitodr@puc.com", "BSI");
        Professor p3 = new Professor("Mariana", "333.333.333-33", "mariana@puc.com", "BSI");

        // Adicionando prof a lista
        professores.add(p1);
        professores.add(p2);
        professores.add(p3);

        // instanciando diciplinas
        disciplinas.add(new Disciplina("Matemática", 140, p1));
        disciplinas.add(new Disciplina("Física", 120, p1));
        disciplinas.add(new Disciplina("POO", 120, p3));
        disciplinas.add(new Disciplina("Experiência Criativa", 120, p2));

        // Inicia interface passando todas as listas
        new Interface(alunos,disciplinas, matriculas);
    }
}
