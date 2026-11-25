# Lista 18 — DAO e Connection Factory

Implementação dos cinco exercícios do PDF, utilizando Java, JDBC e MySQL.

## Organização

Cada classe fica em seu próprio arquivo, separada por exercício, seguindo a organização utilizada nas listas anteriores do repositório.

- `Exercicio01`: modelo `Aluno`, interface `AlunoDAO`, implementação `AlunoDAOImpl` e `Main`.
- `Exercicio02`: modelo `Produto`, interface genérica `GenericDAO`, implementação `ProdutoDAOImpl` e `Main`.
- `Exercicio03`: modelo `Livro`, interface `LivroDAO`, implementação `LivroDAOImpl` e `Main`.
- `Exercicio04`: modelo `Cliente`, interface `ClienteDAO`, implementação `ClienteDAOImpl`, serviço `ClienteService` e `Main`.
- `Exercicio05`: modelos `Pedido` e `ItemPedido`, interfaces e implementações dos dois DAOs, `VendaService` e `Main`.
- `infra/ConnectionFactory.java`: centraliza a criação das conexões com o banco.

## Banco de dados

O enunciado exige operações SQL por meio do JDBC, incluindo inserção, consulta, atualização, exclusão, paginação com `LIMIT/OFFSET` e transações. O script `sql/criar_banco.sql` cria as tabelas utilizadas.

1. Instale Java 17+ e MySQL.
2. Execute `sql/criar_banco.sql` no MySQL.
3. Configure as variáveis de ambiente `DB_URL`, `DB_USER` e `DB_PASSWORD`. A classe `ConnectionFactory` possui valores padrão para uso local.
4. Abra a pasta `Lista18` como projeto Maven na IDE e aguarde o download do MySQL Connector/J.

Cada exercício possui uma classe `Main` para demonstrar o uso. No exercício 5, o `VendaService` abre uma conexão, desativa o autocommit e compartilha a mesma conexão entre os dois DAOs, realizando commit ou rollback.
