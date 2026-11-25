package Lista18.Exercicio05;

public class Pedido {
    private Long id;
    private final Long clienteId;

    public Pedido(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }
}