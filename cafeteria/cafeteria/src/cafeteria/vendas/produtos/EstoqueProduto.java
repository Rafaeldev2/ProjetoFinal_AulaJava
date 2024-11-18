package cafeteria.vendas.produtos;

public class EstoqueProduto {
    private int estoque;


    public EstoqueProduto(int estoque) {
        this.estoque = estoque;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    
    public void atualizarEstoque(int quantidade) {
        this.estoque += quantidade;
    }
    
}
