package Lista18.Exercicio05;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class PedidoDAOImpl implements PedidoDAO {
    @Override
    public long criarPedido(Connection conn, Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (cliente_id) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, pedido.getClienteId());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    pedido.setId(id);
                    return id;
                }
            }
        }
        throw new SQLException("Não foi possível recuperar o ID do pedido.");
    }
}