package Lista17.Exercicio05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContaDAO {
    public static void transferirFundos(Connection conn, Long idOrigem, Long idDestino,
            double valor) throws SQLException {
        if (idOrigem == null || idDestino == null || idOrigem.equals(idDestino)
                || !Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("Informe duas contas distintas e um valor positivo.");
        }

        boolean autoCommitOriginal = conn.getAutoCommit();
        conn.setAutoCommit(false);

        try {
            String sqlDebito = "UPDATE contas SET saldo = saldo - ? WHERE id = ? AND saldo >= ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlDebito)) {
                pstmt.setDouble(1, valor);
                pstmt.setLong(2, idOrigem);
                pstmt.setDouble(3, valor);

                if (pstmt.executeUpdate() != 1) {
                    throw new SQLException("Conta de origem inexistente ou saldo insuficiente.");
                }
            }

            String sqlCredito = "UPDATE contas SET saldo = saldo + ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlCredito)) {
                pstmt.setDouble(1, valor);
                pstmt.setLong(2, idDestino);

                if (pstmt.executeUpdate() != 1) {
                    throw new SQLException("Conta de destino nao encontrada.");
                }
            }

            conn.commit();
            System.out.println("Transferencia realizada com sucesso.");
        } catch (SQLException | RuntimeException erro) {
            try {
                conn.rollback();
            } catch (SQLException erroRollback) {
                erro.addSuppressed(erroRollback);
            }
            throw erro;
        } finally {
            conn.setAutoCommit(autoCommitOriginal);
        }
    }
}
