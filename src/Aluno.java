import java.util.List;
import java.util.ArrayList;

public class Aluno extends Pessoa {
    private int numeroMatricula;
    public Aluno(String nome, String cpf, String email, int numeroMatricula) {
        super(nome, cpf, email);
        this.numeroMatricula = numeroMatricula;
    }
    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    private List<Matricula> listaDeMatriculas = new ArrayList<>();

    @Override
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);
    }

    public void adicionarMatricula(Matricula M) {
        this.listaDeMatriculas.add(M);
    }

    public void listarMatriculas() {
        if (listaDeMatriculas.isEmpty()) {
            System.out.println("O aluno não possui matrículas cadastradas.");
            return;
        }

        System.out.println("Matrículas do aluno:");
        for (Matricula m : listaDeMatriculas) {
            System.out.println("- Descrição: " + m.getDescricao() + " | Código: " + m.getCodigo());
        }
    }

}
