package Lista18.Exercicio01;
import Lista18.infra.ConnectionFactory;
import java.sql.*; import java.util.*;
public class Main {
 static class Aluno { Long id; final String nome,matricula; Aluno(String n,String m){nome=n;matricula=m;} public String toString(){return "Aluno{id="+id+", nome='"+nome+"', matricula='"+matricula+"'}";} }
 interface AlunoDAO { void inserir(Aluno aluno)throws SQLException; List<Aluno> listarTodos()throws SQLException; }
 static class AlunoDAOImpl implements AlunoDAO {
  private final Connection conn; AlunoDAOImpl(Connection c){conn=c;}
  public void inserir(Aluno a)throws SQLException {String sql="INSERT INTO alunos(nome,matricula) VALUES(?,?)"; try(PreparedStatement p=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){p.setString(1,a.nome);p.setString(2,a.matricula);p.executeUpdate();try(ResultSet k=p.getGeneratedKeys()){if(k.next())a.id=k.getLong(1);else throw new SQLException("ID gerado não retornado.");}}}
  public List<Aluno> listarTodos()throws SQLException {List<Aluno> l=new ArrayList<>();try(PreparedStatement p=conn.prepareStatement("SELECT id,nome,matricula FROM alunos ORDER BY id");ResultSet r=p.executeQuery()){while(r.next()){Aluno a=new Aluno(r.getString("nome"),r.getString("matricula"));a.id=r.getLong("id");l.add(a);}}return l;}
 }
 public static void main(String[] args){try(Connection c=ConnectionFactory.getConnection()){AlunoDAO dao=new AlunoDAOImpl(c);Aluno a=new Aluno("Ana Souza","2026001");dao.inserir(a);System.out.println("ID gerado: "+a.id);dao.listarTodos().forEach(System.out::println);}catch(Exception e){System.err.println(e.getMessage());}}
}