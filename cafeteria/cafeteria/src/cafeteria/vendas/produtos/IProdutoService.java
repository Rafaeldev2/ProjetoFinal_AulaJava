package cafeteria.vendas.produtos;

import java.util.List;

public interface IProdutoService {
    void adicionarProduto(Produto produto);
    List<Produto>listaProdutos();
    Produto buscaProduto(int id);
}
