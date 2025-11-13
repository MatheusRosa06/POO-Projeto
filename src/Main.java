public class Main {

    public static void main(String[] args) {


        // teste:
        System.out.println("Sistema iniciado...");

        // instancias:
        Aluno aluno = new Aluno("João", "12345678900", "joao@email.com");
        aluno.exibirInformacoes();

        Professor prof = new Professor("Marina", "98765432100", "marina@email.com");
        prof.exibirInformacoes();



    }
}
