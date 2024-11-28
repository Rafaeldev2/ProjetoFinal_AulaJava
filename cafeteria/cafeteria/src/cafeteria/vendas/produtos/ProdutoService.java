package cafeteria.vendas.produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cafeteria.vendas.clientes.Cliente;


public class ProdutoService implements IProdutoService{

    private String selectSQL = "SELECT id, nome,telefone FROM cliente";
    private String insertSQL = "INSERT INTO produto(id,nome,medida,preco,estoque) VALUES(?, ?, ?, ?, ?)";
    private String updateSQL = "UPDATE produto SET nome = ?, medida = ?, preco= ?, estoque = ?  WHERE id = ?";

   
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
    public Produto pesquisarProduto(int id,Connection conn) {
        Produto produtos = null;
        PreparedStatement psReads = null;
        try {
            psReads = conn.prepareStatement(selectSQL);
            ResultSet rs = psReads.executeQuery();
            if (rs.next()) {
                int produto_id = rs.getInt("id");
                String nome = rs.getString("nome");
                Double preco = rs.getDouble("telefone");
                UnidadeMedida medida = UnidadeMedida.valueOf(rs.getString("medida").toUpperCase());

                produtos = new Produto(produto_id,nome,preco,medida);
                return produtos;
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
        return produtos;
    }

    
}
