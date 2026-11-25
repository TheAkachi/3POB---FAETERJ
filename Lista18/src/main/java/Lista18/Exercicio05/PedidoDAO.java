package Lista18.Exercicio05;
import java.sql.Connection;
import java.sql.SQLException;
public interface PedidoDAO {
    long criarPedido(Connection conn, Pedido pedido) throws SQLException;
}