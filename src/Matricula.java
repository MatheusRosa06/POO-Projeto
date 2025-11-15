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

    public boolean registrarPresenca() {
        if (this.presencas < this.totalAulas) {

            this.presencas++;
            System.out.println("Presença registrada. Total atual: " + this.presencas);
            return true;
        } else {
            System.out.println("Erro: O número de presenças já atingiu o total de aulas (" + this.totalAulas + ").");
            return false;
        }
    }

    public double calcularPercentualPresenca() {
        if (this.totalAulas == 0) {
            return 0.0;
        }
        return ((double)this.presencas / this.totalAulas) * 100;
    }
    
    public void validarPresencaMinima() {

        final double MINIMO_REQUERIDO = 75.0;

        double percentualAtual = this.calcularPercentualPresenca();

        if (percentualAtual < MINIMO_REQUERIDO) {
            String mensagem = "Reprovado por Falta. Frequência: " + percentualAtual
                    + "%. O mínimo exigido é " + MINIMO_REQUERIDO + "%.";

            throw new MatriculaInvalidaException(mensagem);

        } else {
            System.out.println("Frequência OK! Aprovado pela Frequência.");
        }
    }
}
