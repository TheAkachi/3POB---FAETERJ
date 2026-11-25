package Lista18.Exercicio04;

public class Cliente {
    private Long id;
    private String nome;
    private String email;
    private String cpf;

    public Cliente(Long id, String nome, String email, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public Cliente(String nome, String email, String cpf) {
        this(null, nome, email, cpf);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCpf() { return cpf; }
}