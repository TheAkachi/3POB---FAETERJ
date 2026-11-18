package Lista17.Exercicio04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class LogSistemaDAO {
    public static void inserirLoteLog(Connection conn, List<String> mensagensLog)
            throws SQLException {
        // A coluna id deve ter seu valor gerado pelo banco de dados.
        String sql = "INSERT INTO logs_sistema (mensagem, data_registro) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (String mensagem : mensagensLog) {
                pstmt.setString(1, mensagem);
                pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.addBatch();
            }

            pstmt.executeBatch();
        }
    }
}
