package cafeteria.vendas;
// import  java.util.List;


import java.sql.Connection;


public interface IVendaService {


   public Venda pesquisarVenda(int id,Connection connn);
   public void criarVenda(Venda v, Connection conn);
   public void atualizarVenda(Venda v,Connection conn);
   public void listarVenda(Connection conn);
}


