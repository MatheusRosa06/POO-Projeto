public class Aluno extends Pessoa {
    public Aluno(String nome, String cpf, String email)  {
        super(nome, cpf, email);
    }
    private int numeroMatricula;

    @Override
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);

    }

}
