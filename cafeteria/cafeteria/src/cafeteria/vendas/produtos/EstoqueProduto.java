package cafeteria.vendas.produtos;

public class EstoqueProduto extends Produto {
    private int estoque;


    public EstoqueProduto(int id, String nome, double preco, UnidadeMedida medida,int estoque) {
        super(id, nome, preco, medida);
        this.estoque = estoque;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    

    // metodos estoque
	public void MostrarEstoque(){
		System.out.println("Mostrar Estoque" + getEstoque());
	}

	
}
