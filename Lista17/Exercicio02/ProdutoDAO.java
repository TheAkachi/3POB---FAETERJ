package Lista17.Exercicio02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public static List<Produto> buscarPorFaixaPreco(Connection conn, double min, double max)
            throws SQLException {
        String sql = "SELECT id, nome, preco, estoque FROM produtos WHERE preco BETWEEN ? AND ?";
        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, min);
            pstmt.setDouble(2, max);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String nome = rs.getString("nome");
                    double preco = rs.getDouble("preco");
                    int estoque = rs.getInt("estoque");

                    produtos.add(new Produto(id, nome, preco, estoque));
                }
            }
        }

        return produtos;
    }
}
