import java.util.List;
import java.util.ArrayList;

public class Professor extends Pessoa {
    private String areadeAtuacao;
    private List<Disciplina> disciplinasMinistradas;

    //construtor
    public Professor(String nome, String cpf, String email) {
        super(nome, cpf, email);
        this.disciplinasMinistradas = disciplinasMinistradas;;
        this.areadeAtuacao = areadeAtuacao;
    }

    public String getAreadeAtuacao() {
        return areadeAtuacao;
    }
    public void setAreadeAtuacao(String areadeAtuacao) {
        this.areadeAtuacao = areadeAtuacao;
    }

    public List<Disciplinas> getDisciplinasMinistradas() {
        return disciplinasMinistradas;
    }
    public void setDisciplinasMinistradas(List<Disciplina> disciplinasMinistradas) {
        this.disciplinasMinistradas = disciplinasMinistradas;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);
    }
}