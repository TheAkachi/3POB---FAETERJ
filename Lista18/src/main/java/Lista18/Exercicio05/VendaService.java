package Lista18.Exercicio05;
import Lista18.infra.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
public class VendaService {
    private final PedidoDAO pedidoDAO;
    private final ItemPedidoDAO itemPedidoDAO;
    public VendaService(PedidoDAO pedidoDAO, ItemPedidoDAO itemPedidoDAO) {
        this.pedidoDAO = Objects.requireNonNull(pedidoDAO);
        this.itemPedidoDAO = Objects.requireNonNull(itemPedidoDAO);
    }
    public long registrarVenda(Pedido pedido, List<ItemPedido> itens) throws SQLException {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo.");
        }
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("A venda deve possuir pelo menos um item.");
        }
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            try {
                long pedidoId = pedidoDAO.criarPedido(conn, pedido);
                itemPedidoDAO.salvarItens(conn, pedidoId, itens);
                conn.commit();
                return pedidoId;
            } catch (SQLException | RuntimeException e) {
                try {
                    conn.rollback();
                } catch (SQLException erroRollback) {
                    e.addSuppressed(erroRollback);
                }
                throw e;
            }
        }
    }
}