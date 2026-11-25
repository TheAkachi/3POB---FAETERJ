package Lista18.Exercicio05;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
public class ItemPedidoDAOImpl implements ItemPedidoDAO {
    @Override
    public void salvarItens(Connection conn, long pedidoId, List<ItemPedido> itens) throws SQLException {
        String sql = "INSERT INTO itens_pedido (pedido_id, descricao, quantidade, valor_unitario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (ItemPedido item : itens) {
                pstmt.setLong(1, pedidoId);
                pstmt.setString(2, item.getDescricao());
                pstmt.setInt(3, item.getQuantidade());
                pstmt.setBigDecimal(4, item.getValorUnitario());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }
}