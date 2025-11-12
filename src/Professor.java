public class Professor extends Pessoa {
    public Professor(String nome, String cpf, String email) {
        super(nome, cpf, email);
    }
    @Override
    public void exibirInformacoes() {
        // Implementação do método abstrato
        System.out.println("--- Informações do Professor ---");
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Email: " + this.email);
    }
}