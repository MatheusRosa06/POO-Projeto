public class Matricula {
    private int codigo;

    private Aluno aluno;
    private Disciplina disciplina;
    private int totalAulas;
    private int presencas;

    public Matricula(int codigo, Aluno aluno, Disciplina disciplina, int totalAulas, int presencas) {
        this.codigo = codigo;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.totalAulas = totalAulas;
        this.presencas = presencas;
    }

    public int getCodigo() {
        return codigo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public int getTotalAulas() {
        return totalAulas;
    }

    public int getPresencas() {
        return presencas;
    }
}
