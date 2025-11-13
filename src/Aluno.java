public class Aluno extends Pessoa {
    private int numeroMatricula;
    public Aluno(String nome, String cpf, String email, int numeroMatricula)  {
        super(nome, cpf, email);
        this.numeroMatricula = numeroMatricula;
    }
    public int getNumeroMatricula() {
        return numeroMatricula;
        
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);

    }

}
