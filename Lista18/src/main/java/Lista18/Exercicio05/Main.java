package Lista18.Exercicio05;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PedidoDAO pedidoDAO = new PedidoDAOImpl();
        ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAOImpl();
        VendaService vendaService = new VendaService(pedidoDAO, itemPedidoDAO);

        Pedido pedido = new Pedido(1L);
        List<ItemPedido> itens = Arrays.asList(
                new ItemPedido("Teclado", 1, new BigDecimal("120.00")),
                new ItemPedido("Mouse", 2, new BigDecimal("45.00"))
        );

        try {
            long pedidoId = vendaService.registrarVenda(pedido, itens);
            System.out.println("Venda registrada com sucesso. Pedido ID: " + pedidoId);
        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
        }
    }
}