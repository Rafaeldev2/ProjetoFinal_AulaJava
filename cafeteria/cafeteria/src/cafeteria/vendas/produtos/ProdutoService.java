package cafeteria.vendas.produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ProdutoService implements IProdutoService{

    private String insertSQL = "INSERT INTO produto(Id, nome, preco, medida) VALUES(?, ?, ?, ?, ?)";
    private String updateSQL = "UPDATE produto SET nome = ?, preco = ?, medida = ? WHERE id = ?";

   
    @Override
    public void criarProduto(Produto p, Connection conn) {
        PreparedStatement psInsert = null;
       try {
        //  ver quais parâmetros iremos mandar o banco
        psInsert = conn.prepareStatement(insertSQL);
        psInsert.setString(1, String.valueOf(p.getId()));;
        psInsert.setString(2, String.valueOf(p.getNome()));
        psInsert.setString(3, String.valueOf(p.getPreco()));
        psInsert.setString(4, String.valueOf(p.getMedida()));
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
    public void atualizarProduto(Produto p, Connection conn) {
        PreparedStatement psUpdate = null;
        try {
            psUpdate = conn.prepareStatement(updateSQL);
            psUpdate.setString(1, String.valueOf(p.getNome()));
            psUpdate.setString(2, String.valueOf(p.getPreco()));
            psUpdate.setString(3,String.valueOf(p.getMedida()));
            psUpdate.setInt(4,p.getId());
 
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
    public Produto pesquisarProduto(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
