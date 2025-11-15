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

        System.out.println("--- Matrículas do Aluno: ---");

        for (Matricula m : listaDeMatriculas) {
            String nomeDisciplina = m.getDisciplina().getNomeDisciplina();
            System.out.println("\n[Código: " + m.getCodigo() + "]");
            System.out.println("  Disciplina: " + nomeDisciplina);
            System.out.println("  Presenças: " + m.getPresencas() + " / " + m.getTotalAulas() + " aulas");

            // TESTA O STATUS DA FREQUÊNCIA (USANDO A EXCEPTION)
            try {
                m.validarPresencaMinima();
                System.out.println("  Status: Aprovado pela Frequência (>= 75%)");
            } catch (MatriculaInvalidaException e) {
                System.out.println("  Status: **REPROVADO POR FALTA**");
            }
        }
    }
}
