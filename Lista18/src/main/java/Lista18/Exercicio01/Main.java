package Lista18.Exercicio01;

import Lista18.infra.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            AlunoDAO alunoDAO = new AlunoDAOImpl(conn);
            Aluno aluno = new Aluno("Ana Souza", "2026001");
            alunoDAO.inserir(aluno);
            System.out.println("Aluno cadastrado com ID: " + aluno.getId());

            List<Aluno> alunos = alunoDAO.listarTodos();
            for (Aluno item : alunos) {
                System.out.println(item);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar o exercício 1: " + e.getMessage());
        }
    }
}