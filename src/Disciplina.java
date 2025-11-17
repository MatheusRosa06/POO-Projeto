public class Disciplina {

    private String nomeDisciplina;
    private int cargaHoraria;
    private Professor professor;

    //Construtor
    public Disciplina(String nomeDisciplina, int cargaHoraria, Professor professor){
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }
    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void exibirInformacoesDisciplina(){
        System.out.println("Disciplina: " + nomeDisciplina);
        System.out.println("Carga Horaria: " + cargaHoraria);
        System.out.println("Professor: " + professor.getNome());
    }
}
