# Lista 18 — DAO e Connection Factory

Implementação dos cinco exercícios do PDF com Java 17, JDBC e MySQL.

## SQL é necessário?
Sim. O enunciado pede operações de persistência, `ResultSet`, `LIMIT/OFFSET` e transações com `Connection`; portanto, as implementações usam SQL via JDBC. Não é necessário Spring: a injeção de dependência do exercício 4 é feita pelo construtor.

## Executar
1. Instale Java 17+ e MySQL.
2. Execute `sql/criar_banco.sql` no MySQL.
3. Configure `DB_URL`, `DB_USER` e `DB_PASSWORD` (há padrões no `ConnectionFactory`).
4. Abra `Lista18` como projeto Maven na IDE.

Cada exercício tem seu próprio `Main.java`, com as classes e interfaces relacionadas agrupadas para facilitar a leitura e execução independente. Os exemplos usam `PreparedStatement` e fecham recursos JDBC com try-with-resources.
