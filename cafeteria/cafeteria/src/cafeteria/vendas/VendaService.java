package cafeteria.vendas;


import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class VendaService implements IVendaService{
   // o que vamos mandar para o banco de dados


   // private String selectSQL = "SELECT id, nome, email, telefone FROM Clientes";
   private String insertSQL = "INSERT INTO Venda(getId,getDataHora,getCliente,) VALUES(?, ?, ?)";
   private String updateSQL = "UPDATE Clientes SET nome = ?, email = ?, telefone = ? WHERE id = ?";
   // private String deleteSQL = "DELETE FROM Clientes WHERE id = ?";


   @Override
   public void atualizarVenda(Venda v,Connection conn) {
       PreparedStatement psUpdate = null;
       try {
           psUpdate = conn.prepareStatement(updateSQL);
           psUpdate.setString(0, String.valueOf(v.getId()));
           psUpdate.setString(1, String.valueOf(v.getDataHora()));
           psUpdate.setString(2, String.valueOf(v.));
           psUpdate.setInt(3, index);
           psUpdate.execute();
       } catch (SQLException sqle) {
           System.out.println("Erro na execucao da atualizacao: " + sqle.getMessage());
       }  finally {
           try {
               psUpdate.close();
           } catch (SQLException sqle) {
               System.err.println("Nao foi possivel finalizar o statement: " + sqle.getMessage());
           }
           psUpdate = null;
       }
      
   }


   @Override
   public void criarVenda(Venda v, Connection conn) {
      
       PreparedStatement psInsert = null;
       try {
           //  ver quais parâmetros iremos mandar o banco
           psInsert = conn.prepareStatement(insertSQL);
           psInsert.setString(0, String.valueOf(v.getId()));;
           psInsert.setString(1, String.valueOf(v.getDataHora()));
           psInsert.setString(2, String.valueOf(v.getCliente()));
           psInsert.setString(3, String.valueOf(v.getItens()));
           psInsert.setString(4, String.valueOf(v.getDesconto()));
           psInsert.setString(5, String.valueOf(v.getTotal()));
           psInsert.executeUpdate();          
       } catch (SQLException sqle) {
           System.err.println("Erro na insercao: " + sqle.getMessage());
       } finally {
           try {
               psInsert.close();
           } catch (SQLException sqle) {
               System.err.println("Nao foi possivel finalizar o statement: " + sqle.getMessage());
           }
           psInsert = null;
       }
   }


   @Override
   public void listarVenda(Venda v) {
       // TODO Auto-generated method stub
      
   }


   @Override
   public Venda pesquisarVenda(int id) {
       // TODO Auto-generated method stub
       return null;
   }
  
  
}
