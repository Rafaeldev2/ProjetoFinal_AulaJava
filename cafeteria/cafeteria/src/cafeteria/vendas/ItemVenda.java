package cafeteria.vendas;

public class ItemVenda {
    private String nomeProduto;
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
