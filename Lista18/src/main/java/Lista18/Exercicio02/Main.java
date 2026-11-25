package Lista18.Exercicio02;

import Lista18.infra.ConnectionFactory;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            GenericDAO<Produto, Long> produtoDAO = new ProdutoDAOImpl(conn);
            Produto produto = new Produto("Teclado", new BigDecimal("120.00"), 10);
            produtoDAO.salvar(produto);
            System.out.println("Produto cadastrado com ID: " + produto.getId());

            Optional<Produto> encontrado = produtoDAO.buscarPorId(produto.getId());
            System.out.println("Busca por ID: " + encontrado.orElse(null));

            produto.setQuantidadeEstoque(8);
            produtoDAO.atualizar(produto);
            System.out.println("Produto atualizado.");

            List<Produto> produtos = produtoDAO.listarTodos();
            for (Produto item : produtos) {
                System.out.println(item);
            }

            // Descomente para testar: produtoDAO.deletarPorId(produto.getId());
        } catch (SQLException e) {
            System.out.println("Erro ao executar o exercício 2: " + e.getMessage());
        }
    }
}