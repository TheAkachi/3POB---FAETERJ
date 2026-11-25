package Lista18.Exercicio03;
import Lista18.infra.ConnectionFactory; import java.sql.*; import java.util.*;
public class Main {
 static class Livro {final long id;final String titulo,autor;Livro(long i,String t,String a){id=i;titulo=t;autor=a;}public String toString(){return "Livro{"+id+", "+titulo+", "+autor+"}";}}
 interface LivroDAO{List<Livro> buscarPaginado(int pagina,int tamanhoPagina)throws SQLException;}
 static class LivroDAOImpl implements LivroDAO{final Connection c;LivroDAOImpl(Connection c){this.c=c;}public List<Livro> buscarPaginado(int pagina,int tamanhoPagina)throws SQLException{if(pagina<1||tamanhoPagina<1)throw new IllegalArgumentException("Página e tamanho devem ser positivos.");int offset=Math.multiplyExact(pagina-1,tamanhoPagina);List<Livro> l=new ArrayList<>();String sql="SELECT id,titulo,autor FROM livros ORDER BY id LIMIT ? OFFSET ?";try(PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,tamanhoPagina);p.setInt(2,offset);try(ResultSet r=p.executeQuery()){while(r.next())l.add(new Livro(r.getLong("id"),r.getString("titulo"),r.getString("autor")));}}return l;}}
 public static void main(String[] args){try(Connection c=ConnectionFactory.getConnection()){new LivroDAOImpl(c).buscarPaginado(1,10).forEach(System.out::println);}catch(Exception e){System.err.println(e.getMessage());}}
}