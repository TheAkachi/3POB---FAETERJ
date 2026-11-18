package Lista17.Exercicio01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDAO {
    public static long inserirCliente(Connection conn, String nome, String email) throws SQLException {
        String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, email);

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhum cliente foi inserido.");
            }

            try (ResultSet chaves = pstmt.getGeneratedKeys()) {
                if (chaves.next()) {
                    long idGerado = chaves.getLong(1);
                    System.out.println("Cliente inserido com ID: " + idGerado);
                    return idGerado;
                }
            }

            throw new SQLException("O banco nao retornou o ID gerado.");
        }
    }
}
