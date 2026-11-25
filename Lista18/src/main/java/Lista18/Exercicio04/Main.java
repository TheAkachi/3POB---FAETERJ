package Lista18.Exercicio04;

import Lista18.infra.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            ClienteDAO clienteDAO = new ClienteDAOImpl(conn);
            ClienteService clienteService = new ClienteService(clienteDAO);

            Cliente cliente = new Cliente("João Lima", "joao@email.com", "12345678900");
            clienteService.cadastrarCliente(cliente);
            System.out.println("Cliente cadastrado com ID: " + cliente.getId());
        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Erro ao executar o exercício 4: " + e.getMessage());
        }
    }
}