package cafeteria.vendas.produtos;

import java.sql.Connection;
import java.util.ArrayList;

public interface IProdutoService {

   public Produto pesquisarProduto(int id,Connection conn);
   public void criarProduto(Produto p, Connection conn);
   public void atualizarProduto(Produto p, Connection conn);
   public ArrayList<Produto> listarProdutos(Connection conn);
}
