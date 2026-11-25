package Lista18.Exercicio03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAOImpl implements LivroDAO {
    private final Connection conn;

    public LivroDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Livro> buscarPaginado(int pagina, int tamanhoPagina) throws SQLException {
        if (pagina < 1 || tamanhoPagina < 1) {
            throw new IllegalArgumentException("Página e tamanho da página devem ser maiores que zero.");
        }

        int offset = (pagina - 1) * tamanhoPagina;
        String sql = "SELECT * FROM livros ORDER BY id LIMIT ? OFFSET ?";
        List<Livro> livros = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tamanhoPagina);
            pstmt.setInt(2, offset);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    livros.add(new Livro(rs.getLong("id"), rs.getString("titulo"), rs.getString("autor")));
                }
            }
        }

        return livros;
    }
}