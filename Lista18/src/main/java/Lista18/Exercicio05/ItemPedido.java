package Lista18.Exercicio05;

import java.math.BigDecimal;

public class ItemPedido {
    private final String descricao;
    private final Integer quantidade;
    private final BigDecimal valorUnitario;

    public ItemPedido(String descricao, Integer quantidade, BigDecimal valorUnitario) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public String getDescricao() { return descricao; }
    public Integer getQuantidade() { return quantidade; }
    public BigDecimal getValorUnitario() { return valorUnitario; }
}