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
}
