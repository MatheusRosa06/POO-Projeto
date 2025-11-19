import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorCSVAluno {

    private static final String CAMINHO_ARQUIVO = "alunos.csv";

    // -----------------------------------------
    // LER ALUNOS DO CSV
    // -----------------------------------------
    public static List<Aluno> lerAlunos() {
        List<Aluno> lista = new ArrayList<>();
        File arquivo = new File(CAMINHO_ARQUIVO);

        if (!arquivo.exists()) {
            return lista; // se não existe, retorna lista vazia
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");

                // Espera nome, cpf, email, matricula
                if (dados.length < 4) continue;

                String nome = dados[0].trim();
                String cpf = dados[1].trim();
                String email = dados[2].trim();
                int matricula = Integer.parseInt(dados[3].trim());

                // Importante: usa o construtor correto (4 argumentos)
                Aluno aluno = new Aluno(nome, cpf, email, matricula);
                lista.add(aluno);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // -----------------------------------------
    // GRAVAR LISTA DE ALUNOS NO CSV
    // -----------------------------------------
    public static void gravarAlunos(List<Aluno> alunos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {

            for (Aluno a : alunos) {
                bw.write(
                        a.getNome() + "," +
                                a.getCpf() + "," +
                                a.getEmail() + "," +
                                a.getNumeroMatricula()
                );
                bw.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
