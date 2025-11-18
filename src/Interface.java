import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Interface {

    private JFrame frameCadastro;
    private JFrame frameGerenciamento;

    private ArrayList<Aluno> alunos;
    private ArrayList<Disciplina> disciplinas;
    private ArrayList<Matricula> matriculas;

    private JComboBox<Integer> alunoBox; // atributo compartilhado entre telas

    public Interface(ArrayList<Aluno> alunos,
                     ArrayList<Disciplina> disciplinas,
                     ArrayList<Matricula> matriculas) {

        this.alunos = alunos;
        this.disciplinas = disciplinas;
        this.matriculas = matriculas;

        criarTelaCadastro();
        criarTelaGerenciamento();
    }


    //   TELA 1 — Cadatro de aluno

    private void criarTelaCadastro() {

        //layout da tela
        frameCadastro = new JFrame("Cadastro de Aluno");
        frameCadastro.setSize(450, 500);
        frameCadastro.setLayout(new GridLayout(10, 1));
        frameCadastro.setLocation(100, 100);
        frameCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField matriculaField = new JTextField();

        //campos a preencher
        frameCadastro.add(new JLabel("Nome:"));
        frameCadastro.add(nomeField);

        frameCadastro.add(new JLabel("CPF:"));
        frameCadastro.add(cpfField);

        frameCadastro.add(new JLabel("Email:"));
        frameCadastro.add(emailField);

        frameCadastro.add(new JLabel("Número da Matrícula (único):"));
        frameCadastro.add(matriculaField);

        // lista múltipla de disciplinas
        DefaultListModel<String> modeloDisc = new DefaultListModel<>();
        for (Disciplina d : disciplinas) {
            modeloDisc.addElement(d.getNomeDisciplina());
        }

        JList<String> listaDisciplinas = new JList<>(modeloDisc);
        listaDisciplinas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        frameCadastro.add(new JLabel("Selecione Disciplinas:"));
        frameCadastro.add(new JScrollPane(listaDisciplinas));

        JButton btnCadastrar = new JButton("Cadastrar Aluno");

        btnCadastrar.addActionListener((ActionEvent e) -> {

            try {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String email = emailField.getText();
                int codMat = Integer.parseInt(matriculaField.getText());

                Aluno aluno = new Aluno(nome, cpf, email, codMat);
                alunos.add(aluno);

                // Atualiza combo box do gerenciamento
                alunoBox.addItem(aluno.getNumeroMatricula());

                for (int i : listaDisciplinas.getSelectedIndices()) {
                    Disciplina disc = disciplinas.get(i);

                    int totalAulas = disc.getCargaHoraria();

                    int presencas = Integer.parseInt(
                            JOptionPane.showInputDialog("Presenças iniciais em " + disc.getNomeDisciplina())
                    );

                    Matricula m = new Matricula(codMat, aluno, disc, totalAulas, presencas);
                    matriculas.add(m);

                    System.out.println(">> Matricula criada para " + disc.getNomeDisciplina());
                }

                System.out.println("Aluno cadastrado: " + nome);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frameCadastro.add(btnCadastrar);

        frameCadastro.setVisible(true);
    }


    //   TELA 2 — Gerenciamento de presenças

    private void criarTelaGerenciamento() {

        //layout da tela
        frameGerenciamento = new JFrame("Gerenciamento de Presenças");
        frameGerenciamento.setSize(450, 500);
        frameGerenciamento.setLayout(new GridLayout(10, 1));
        frameGerenciamento.setLocation(600, 100);
        frameGerenciamento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // <<< usar atributo da classe,
        alunoBox = new JComboBox<>();
        JComboBox<String> discBox = new JComboBox<>();

        //botoes para gerenciar dados
        JButton carregarBtn = new JButton("Carregar Disciplinas");
        JButton registrarBtn = new JButton("Registrar Presença");
        JButton validarBtn = new JButton("Validar Frequência");

        frameGerenciamento.add(new JLabel("Selecione Aluno (Matrícula):"));
        frameGerenciamento.add(alunoBox);

        frameGerenciamento.add(new JLabel("Disciplinas:"));
        frameGerenciamento.add(discBox);

        frameGerenciamento.add(carregarBtn);
        frameGerenciamento.add(registrarBtn);
        frameGerenciamento.add(validarBtn);

        // carregar alunos já existentes pela matricula
        for (Aluno a : alunos) {
            alunoBox.addItem(a.getNumeroMatricula());
        }

        //botao "carregar diciplinas
        carregarBtn.addActionListener((ActionEvent e) -> {
            discBox.removeAllItems();
            int mat = (int) alunoBox.getSelectedItem();

            for (Matricula m : matriculas) {
                if (m.getAluno().getNumeroMatricula() == mat) {
                    discBox.addItem(m.getDisciplina().getNomeDisciplina());
                }
            }
        });

        //botao "registra presença"
        registrarBtn.addActionListener((ActionEvent e) -> {
            int mat = (int) alunoBox.getSelectedItem();
            String disc = (String) discBox.getSelectedItem();

            for (Matricula m : matriculas) {
                if (m.getAluno().getNumeroMatricula() == mat &&
                        m.getDisciplina().getNomeDisciplina().equals(disc)) {

                    m.registrarPresenca(); // prints da classe Matricula
                    break;
                }
            }
        });

        //botao "validar frequência"
        validarBtn.addActionListener((ActionEvent e) -> {
            int mat = (int) alunoBox.getSelectedItem();
            String disc = (String) discBox.getSelectedItem();

            for (Matricula m : matriculas) {
                if (m.getAluno().getNumeroMatricula() == mat &&
                        m.getDisciplina().getNomeDisciplina().equals(disc)) {

                    try {
                        m.validarPresencaMinima();
                    } catch (MatriculaInvalidaException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        frameGerenciamento.setVisible(true);
    }
}
