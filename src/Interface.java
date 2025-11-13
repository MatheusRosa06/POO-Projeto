import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {

    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoEmail;
    private JComboBox<String> comboTipo;
    private JTextArea areaResultado;
    private JButton botaoCadastrar;

    public Interface() {
        setTitle("Cadastro de Pessoas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 5, 5));
        setLocationRelativeTo(null); // centraliza na tela

        // Componentes
        JLabel labelTipo = new JLabel("Tipo:");
        comboTipo = new JComboBox<>(new String[]{"Aluno", "Professor"});

        JLabel labelNome = new JLabel("Nome:");
        campoNome = new JTextField();

        JLabel labelCpf = new JLabel("CPF:");
        campoCpf = new JTextField();

        JLabel labelEmail = new JLabel("Email:");
        campoEmail = new JTextField();

        botaoCadastrar = new JButton("Cadastrar");
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        // Adiciona componentes
        add(labelTipo);
        add(comboTipo);
        add(labelNome);
        add(campoNome);
        add(labelCpf);
        add(campoCpf);
        add(labelEmail);
        add(campoEmail);
        add(botaoCadastrar);
        add(new JLabel());
        add(new JLabel("Resultado:"));
        add(areaResultado);

        // Ação do botão
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarPessoa();
            }
        });
    }

    private void cadastrarPessoa() {
        String tipo = comboTipo.getSelectedItem().toString().toLowerCase();
        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().trim();
        String email = campoEmail.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        Pessoa p;
        if (tipo.equals("aluno")) {
            p = new Aluno(nome, cpf, email);
        } else {
            p = new Professor(nome, cpf, email);
        }

        // Mostra informações na tela e console
        p.exibirInformacoes();
        areaResultado.setText("Cadastro realizado com sucesso!\n\n" +
                "Tipo: " + tipo + "\n" +
                "Nome: " + p.getNome() + "\n" +
                "CPF: " + p.getCpf() + "\n" +
                "Email: " + p.getEmail());
    }
}
