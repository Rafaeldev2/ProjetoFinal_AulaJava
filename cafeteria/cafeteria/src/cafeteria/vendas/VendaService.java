package cafeteria.vendas;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VendaService implements IVendaService{
   // o que vamos mandar para o banco de dados


   private String selectSQL = "SELECT id, data_hora,cliente_id,desconto FROM venda";
   private String insertSQL = "INSERT INTO venda(id, data_hora,cliente_id,desconto) VALUES(?, ?, ?, ?, ?)";
   private String updateSQL = "UPDATE venda SET data_hora = ?, cliente_id = ?,Desconto = ?, WHERE id = ?";
   private String pesquisaSQL = "SELECT * FROM venda WHERE id = ?";
   // private String deleteSQL = "DELETE FROM Clientes WHERE id = ?";

    @Override
   public void criarVenda(Venda v, Connection conn) {
      
       PreparedStatement psInsert = null;
       try {
           //  ver quais parâmetros iremos mandar o banco
           psInsert = conn.prepareStatement(insertSQL);
           psInsert.setString(1, String.valueOf(v.getId()));;
           psInsert.setString(2, String.valueOf(v.getDataHora()));
           psInsert.setString(3, String.valueOf(v.getCliente()));
        //    psInsert.setString(3, String.valueOf(v.getItens()));
           psInsert.setString(4, String.valueOf(v.getDesconto()));
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
   public void atualizarVenda(Venda v,Connection conn) {
       PreparedStatement psUpdate = null;
       try {
           psUpdate = conn.prepareStatement(updateSQL);
           psUpdate.setString(1, String.valueOf(v.getDataHora()));
           psUpdate.setString(2, String.valueOf(v.getCliente()));
           psUpdate.setString(3,String.valueOf(v.getDesconto()));
           psUpdate.setInt(4,v.getId());

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
   public void listarVenda(Connection conn) {
        ArrayList<Venda> vendas = new ArrayList<>();
        PreparedStatement psReads = null;
                try {
                psReads = conn.prepareStatement(selectSQL);
                ResultSet rs = psReads.executeQuery();
                while (rs.next()) {
    
                    vendas.add(new Venda(
                        // Duas formas que podemos fazer

                        // 0, 
                        // null, 
                        // null, 
                        // null, 
                        // 0, 
                        // 0

                        rs.getInt("id"), 
                        rs.getTimestamp("data_hora"),  
                        // argumentos adicionados para corresponder o construtor de venda
                        null, 
                        null, 
                        rs.getInt("cliente_id"),
                        rs.getDouble("desconto")
                ));
            }
            } catch (SQLException sqle) {
                System.err.println("Erro na consulta: " + sqle.getMessage());
            } finally {
                try {
                    psReads.close();
                } catch (SQLException sqle) {
                    System.err.println("Nao foi possivel finalizar o statement: " + sqle.getMessage());
                }
                psReads = null;
            }
            return;
       }


   @Override
   public Venda pesquisarVenda(int id, Connection conn) {
    PreparedStatement psReads = null;
    Venda venda = null; 
    
    try{
        psReads = conn.prepareStatement(pesquisaSQL);
        psReads.setInt(1, id);
        ResultSet rs = psReads.executeQuery();
    
        if(rs.next()) {
            venda = new Venda(
                rs.getInt("id"),
                rs.getTimestamp("data_hora"),
                rs.getInt("cliente_id"),
                rs.getDouble("desconto")
            );
        }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao pesquisar venda: " + e.getMessage());
        } finally {
            try {
                if (psReads != null) {
                    psReads.close(); 
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return venda; 
 
    }
}