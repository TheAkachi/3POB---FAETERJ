package Lista18.Exercicio04;

import java.sql.SQLException;
import java.util.Objects;

public class ClienteService {
    private final ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = Objects.requireNonNull(clienteDAO);
    }

    public void cadastrarCliente(Cliente cliente) throws SQLException {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            throw new IllegalArgumentException("E-mail não pode ser nulo ou vazio.");
        }
        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        if (clienteDAO.buscarPorCpf(cliente.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF.");
        }
        clienteDAO.salvar(cliente);
    }
}