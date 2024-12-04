package cafeteria.vendas.produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoService implements IProdutoService{

    private String selectSQL = "SELECT * FROM produto WHERE id = ?";
    private String insertSQL = "INSERT INTO produto(nome,medida,preco,estoque) VALUES(?, ?, ?, ?)";
    private String updateSQL = "UPDATE produto SET nome = ?, medida = ?, preco= ?, estoque = ?  WHERE id = ?";

   
    @Override
    public void criarProduto(Produto p, Connection conn) {
        PreparedStatement psInsert = null;
       try {
        psInsert = conn.prepareStatement(insertSQL);
        psInsert.setString(1, p.getNome());
        psInsert.setInt(2, p.getMedida().getCodigo());
        psInsert.setDouble(3, p.getPreco());
        psInsert.setDouble(4, ((EstoqueProduto) p).getEstoque());
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
            psUpdate.setString(1, p.getNome());
            psUpdate.setInt(2, p.getMedida().getCodigo()); // Usando o código da UnidadeMedida
            psUpdate.setDouble(3, p.getPreco());
            psUpdate.setDouble(4, ((EstoqueProduto) p).getEstoque());
            psUpdate.setInt(5, p.getId());

            int rowsAffected = psUpdate.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Nenhum produto encontrado/atualizado com o ID: " + p.getId());
            }
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
            psReads.setInt(1, id);
            ResultSet rs = psReads.executeQuery();
            if (rs.next()) {
                int produto_id = rs.getInt("id");
                String nome = rs.getString("nome");
                Double preco = rs.getDouble("preco");
                int codigoMedida = rs.getInt("medida");
                UnidadeMedida medida = UnidadeMedida.fromCodigo(codigoMedida);
                int estoque = rs.getInt("estoque");
                if (estoque > 0) {
                    produtos = new EstoqueProduto(produto_id, nome, preco, medida, estoque);
                } else {
                    produtos = new Produto(produto_id, nome, preco, medida); // Se não houver estoque, pode ser 0
                }
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
