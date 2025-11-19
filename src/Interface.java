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

    private JComboBox<Integer> alunoBox;

    private void atualizarComboAlunos() {
        alunoBox.removeAllItems();
        for (Aluno a : alunos) {
            alunoBox.addItem(a.getNumeroMatricula());
        }
    }

    public Interface(ArrayList<Aluno> alunos,
                     ArrayList<Disciplina> disciplinas,
                     ArrayList<Matricula> matriculas) {

        this.alunos = alunos;
        this.disciplinas = disciplinas;
        this.matriculas = matriculas;

        criarTelaCadastro();
        criarTelaGerenciamento();
    }

    // ------------------------------------------------------------------
    // TELA 1 — Cadastro de aluno (AGORA COM CHECKBOXES)
    // ------------------------------------------------------------------

    private void criarTelaCadastro() {

        frameCadastro = new JFrame("Cadastro de Aluno");
        frameCadastro.setSize(450, 500);
        frameCadastro.setLayout(new GridLayout(10, 1));
        frameCadastro.setLocation(100, 100);
        frameCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField matriculaField = new JTextField();

        frameCadastro.add(new JLabel("Nome:"));
        frameCadastro.add(nomeField);

        frameCadastro.add(new JLabel("CPF:"));
        frameCadastro.add(cpfField);

        frameCadastro.add(new JLabel("Email:"));
        frameCadastro.add(emailField);

        frameCadastro.add(new JLabel("Número da Matrícula (único):"));
        frameCadastro.add(matriculaField);

        // PAINEL COM CHECKBOX PARA CADA DISCIPLINA
        JPanel painelChecks = new JPanel();
        painelChecks.setLayout(new GridLayout( disciplinas.size(), 1 ));

        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();

        for (Disciplina d : disciplinas) {
            JCheckBox check = new JCheckBox(d.getNomeDisciplina());
            painelChecks.add(check);
            checkBoxes.add(check);
        }

        JScrollPane scroll = new JScrollPane(painelChecks);
        scroll.setPreferredSize(new Dimension(200, 120));

        frameCadastro.add(new JLabel("Selecione Disciplinas:"));
        frameCadastro.add(scroll);

        JButton btnCadastrar = new JButton("Cadastrar Aluno");

        btnCadastrar.addActionListener((ActionEvent e) -> {

            try {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String email = emailField.getText();
                int codMat = Integer.parseInt(matriculaField.getText());

                for (Aluno a : alunos) {
                    if (a.getNumeroMatricula() == codMat) {
                        JOptionPane.showMessageDialog(null,
                                "Erro: já existe um aluno com essa matrícula!");
                        return;
                    }
                }

                Aluno aluno = new Aluno(nome, cpf, email, codMat);
                alunos.add(aluno);
                atualizarComboAlunos();

                // --- PEGA SOMENTE AS DISCIPLINAS QUE FORAM MARCADAS ---
                for (int i = 0; i < checkBoxes.size(); i++) {
                    JCheckBox box = checkBoxes.get(i);

                    if (box.isSelected()) {
                        Disciplina disc = disciplinas.get(i);

                        int totalAulas = disc.getCargaHoraria();

                        int presencas = Integer.parseInt(
                                JOptionPane.showInputDialog("Presenças iniciais em " + disc.getNomeDisciplina())
                        );

                        Matricula m = new Matricula(codMat, aluno, disc, totalAulas, presencas);
                        matriculas.add(m);
                        aluno.adicionarMatricula(m); // mantém seu fluxo original
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frameCadastro.add(btnCadastrar);
        frameCadastro.setVisible(true);
    }

    // ------------------------------------------------------------------
    // TELA 2 — Gerenciamento de presença (NADA ALTERADO)
    // ------------------------------------------------------------------

    private void criarTelaGerenciamento() {

        frameGerenciamento = new JFrame("Gerenciamento de Presenças");
        frameGerenciamento.setSize(450, 500);
        frameGerenciamento.setLayout(new GridLayout(10, 1));
        frameGerenciamento.setLocation(600, 100);
        frameGerenciamento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        alunoBox = new JComboBox<>();
        JComboBox<String> discBox = new JComboBox<>();

        JButton carregarBtn = new JButton("Carregar Disciplinas");
        JButton registrarBtn = new JButton("Registrar Presença");
        JButton validarBtn = new JButton("Validar Frequência");
        JButton btnListarAlunos = new JButton("Listar Todos os Alunos");

        frameGerenciamento.add(new JLabel("Selecione Aluno (Matrícula):"));
        frameGerenciamento.add(alunoBox);

        frameGerenciamento.add(new JLabel("Disciplinas:"));
        frameGerenciamento.add(discBox);

        frameGerenciamento.add(carregarBtn);
        frameGerenciamento.add(registrarBtn);
        frameGerenciamento.add(validarBtn);
        frameGerenciamento.add(btnListarAlunos);

        atualizarComboAlunos();

        carregarBtn.addActionListener((ActionEvent e) -> {
            discBox.removeAllItems();
            int mat = (int) alunoBox.getSelectedItem();

            ArrayList<String> adicionadas = new ArrayList<>();

            for (Matricula m : matriculas) {
                if (m.getAluno().getNumeroMatricula() == mat) {

                    String nomeDisc = m.getDisciplina().getNomeDisciplina();

                    if (!adicionadas.contains(nomeDisc)) {
                        discBox.addItem(nomeDisc);
                        adicionadas.add(nomeDisc);
                    }
                }
            }
        });

        registrarBtn.addActionListener((ActionEvent e) -> {
            int mat = (int) alunoBox.getSelectedItem();
            String disc = (String) discBox.getSelectedItem();

            for (Matricula m : matriculas) {
                if (m.getAluno().getNumeroMatricula() == mat &&
                        m.getDisciplina().getNomeDisciplina().equals(disc)) {

                    m.registrarPresenca();
                    break;
                }
            }
        });

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

        btnListarAlunos.addActionListener(e -> {

            StringBuilder sb = new StringBuilder("Alunos cadastrados:\n\n");

            for (Aluno a : alunos) {
                sb.append("Nome: ").append(a.getNome()).append("\n");
                sb.append("Email: ").append(a.getEmail()).append("\n");
                sb.append("Matrícula: ").append(a.getNumeroMatricula()).append("\n");
                sb.append("-----------------------------\n");
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        });

        frameGerenciamento.setVisible(true);
    }
}
