package cafeteria.vendas;

import cafeteria.vendas.produtos.UnidadeMedida;

public class ItemVenda {
	private int id;
    private String nomeProduto;
    private UnidadeMedida medida;
    private int quantidade;
    private double precoUnitario;


    public ItemVenda(int id, String nomeProduto, UnidadeMedida medida, int quantidade, double precoUnitario) {
		super();
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.medida = medida;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}

	public int getId() {
		return id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public UnidadeMedida getMedida() {
		return medida;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public void setMedida(UnidadeMedida medida) {
		this.medida = medida;
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
