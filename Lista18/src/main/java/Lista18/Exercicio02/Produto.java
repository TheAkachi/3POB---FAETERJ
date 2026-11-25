package Lista18.Exercicio02;

import java.math.BigDecimal;

public class Produto {
    private Long id;
    private String descricao;
    private BigDecimal precoUnitario;
    private Integer quantidadeEstoque;

    public Produto(Long id, String descricao, BigDecimal precoUnitario, Integer quantidadeEstoque) {
        this.id = id;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Produto(String descricao, BigDecimal precoUnitario, Integer quantidadeEstoque) {
        this(null, descricao, precoUnitario, quantidadeEstoque);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    @Override
    public String toString() {
        return "Produto{id=" + id + ", descricao='" + descricao + "', precoUnitario="
                + precoUnitario + ", quantidadeEstoque=" + quantidadeEstoque + "}";
    }
}