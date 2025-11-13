public class Professor extends Pessoa {
    public Professor(String nome, String cpf, String email) {
        super(nome, cpf, email);
    }
    private String areadeAtuacao;
    public String getAreadeAtuacao() {
        return areadeAtuacao;

    }
    @Override
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);
    }
}