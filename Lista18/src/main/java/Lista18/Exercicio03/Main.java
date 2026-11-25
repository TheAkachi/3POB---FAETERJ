package Lista18.Exercicio03;

import Lista18.infra.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            LivroDAO livroDAO = new LivroDAOImpl(conn);
            int pagina = 1;
            int tamanhoPagina = 10;

            List<Livro> livros = livroDAO.buscarPaginado(pagina, tamanhoPagina);
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar o exercício 3: " + e.getMessage());
        }
    }
}