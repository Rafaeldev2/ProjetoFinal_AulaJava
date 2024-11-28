package cafeteria.vendas.produtos;

import java.sql.Connection;

public interface IProdutoService {

   public Produto pesquisarProduto(int id,Connection conn);
   public void criarProduto(Produto p, Connection conn);
   public void atualizarProduto(Produto p, Connection conn);
}
