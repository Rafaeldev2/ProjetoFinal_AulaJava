package cafeteria.vendas;

import cafeteria.vendas.produtos.UnidadeMedida;

public class ItemVenda {
	private int id;
    private String nomeProduto;
    private UnidadeMedida Medida;
    private int quantidade;
    private double precoUnitario;

    public ItemVenda() {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    // gets

    public String getNomeProduto() {
        return nomeProduto;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    // sets

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
   
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }


    // metodo para calcular total do valor do item
    public double TotalItem(){
            return this.quantidade * this.precoUnitario;
    }

    

}
