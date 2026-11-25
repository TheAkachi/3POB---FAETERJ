package Lista18.Exercicio04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class ClienteDAOImpl implements ClienteDAO {
    private final Connection conn;

    public ClienteDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT id, nome, email, cpf FROM clientes WHERE cpf = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(rs.getLong("id"), rs.getString("nome"),
                            rs.getString("email"), rs.getString("cpf"));
                    return Optional.of(cliente);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void salvar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, email, cpf) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getCpf());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getLong(1));
                }
            }
        }
    }
}