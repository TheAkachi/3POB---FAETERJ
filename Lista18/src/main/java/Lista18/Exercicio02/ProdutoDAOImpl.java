package Lista18.Exercicio02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoDAOImpl implements GenericDAO<Produto, Long> {
    private final Connection conn;

    public ProdutoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (descricao, preco_unitario, quantidade_estoque) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preencherParametros(pstmt, produto);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    produto.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) throws SQLException {
        String sql = "SELECT id, descricao, preco_unitario, quantidade_estoque FROM produtos WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearProduto(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Produto> listarTodos() throws SQLException {
        String sql = "SELECT id, descricao, preco_unitario, quantidade_estoque FROM produtos ORDER BY id";
        List<Produto> produtos = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                produtos.add(mapearProduto(rs));
            }
        }
        return produtos;
    }

    @Override
    public void atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE produtos SET descricao = ?, preco_unitario = ?, quantidade_estoque = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            preencherParametros(pstmt, produto);
            pstmt.setLong(4, produto.getId());
            if (pstmt.executeUpdate() == 0) {
                throw new SQLException("Nenhum produto encontrado com o ID informado.");
            }
        }
    }

    @Override
    public void deletarPorId(Long id) throws SQLException {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            if (pstmt.executeUpdate() == 0) {
                throw new SQLException("Nenhum produto encontrado com o ID informado.");
            }
        }
    }

    private void preencherParametros(PreparedStatement pstmt, Produto produto) throws SQLException {
        pstmt.setString(1, produto.getDescricao());
        pstmt.setBigDecimal(2, produto.getPrecoUnitario());
        pstmt.setInt(3, produto.getQuantidadeEstoque());
    }

    private Produto mapearProduto(ResultSet rs) throws SQLException {
        return new Produto(rs.getLong("id"), rs.getString("descricao"),
                rs.getBigDecimal("preco_unitario"), rs.getInt("quantidade_estoque"));
    }
}