package Lista18.Exercicio01;

import java.sql.SQLException;
import java.util.List;

public interface AlunoDAO {
    void inserir(Aluno aluno) throws SQLException;

    List<Aluno> listarTodos() throws SQLException;
}
