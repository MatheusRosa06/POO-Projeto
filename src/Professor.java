    import java.util.List;
    import java.util.ArrayList;
    
    public class Professor extends Pessoa {
        private String areadeAtuacao;
        private List<Disciplina> disciplinasMinistradas;

        //construtor
        public Professor(String nome, String cpf, String email, String areadeAtuacao) {
            super(nome, cpf, email);
            this.areadeAtuacao = areadeAtuacao;
            this.disciplinasMinistradas = new ArrayList<>();
        }

        //get areadeAtuacao
        public String getAreadeAtuacao() {
            return areadeAtuacao;
        }

        //set areadeAtuacao
        public void setAreadeAtuacao(String areadeAtuacao) {
            this.areadeAtuacao = areadeAtuacao;
        }

        //get disciplinasMinistradas
        public List<Disciplina> getDisciplinasMinistradas() {
            return disciplinasMinistradas;
        }

        public void adicionarDisciplina(Disciplina d) {
            disciplinasMinistradas.add(d);
            d.setProfessor(this);
        }

        //sobrescrita do metodo abstrato da classe pessoa
        @Override
        public void exibirInformacoes() {
            System.out.println("Nome: " + nome);
            System.out.println("CPF: " + cpf);
            System.out.println("Email: " + email);
            System.out.println("Área de atuação: " + areadeAtuacao);
            System.out.println("Disciplinas Ministradas:");


        }
        //metodo sobrescrito
        public void saudacao() {
            System.out.println("Olá! Eu sou o professor " + this.nome);
        }
    }