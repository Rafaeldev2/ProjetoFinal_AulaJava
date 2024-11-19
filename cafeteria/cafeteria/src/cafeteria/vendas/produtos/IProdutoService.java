package cafeteria.vendas.produtos;

public interface IProdutoService {

   public Produto pesquisarProduto(int id);
   public void criarProduto(Produto p);
   public void atualizarProduto(Produto p);
}
