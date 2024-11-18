package cafeteria.vendas;
import java.time.LocalDate;
import  java.util.List;


public class Venda {
    private int id;
    private LocalDate dataHora;
    private Cliente cliente;
    private List<ItemVenda> itens;
    private double  desconto;
    private double total;

    
    


public Venda(int id, LocalDate dataHora, Cliente cliente, List<ItemVenda> itens, double desconto, double total) {
		super();
		this.id = id;
		this.dataHora = dataHora;
		this.cliente = cliente;
		this.itens = itens;
		this.desconto = desconto;
		this.total = total;
	}

  
    
public int getId() {
	return id;
}


public LocalDate getDataHora() {
	return dataHora;
}


public Cliente getCliente() {
	return cliente;
}

public List<ItemVenda> getItens() {
	return itens;
}

public double getDesconto() {
	return desconto;
}

public double getTotal() {
	return total;
}

public void setId(int id) {
	this.id = id;
}

public void setDataHora(LocalDate dataHora) {
	this.dataHora = dataHora;
}

public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}


public void setItens(List<ItemVenda> itens) {
	this.itens = itens;
}

public void setDesconto(double desconto) {
	this.desconto = desconto;
}

public void setTotal(double total) {
	this.total = total;
}

	// manipulação de itens
    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
    }

    //  metodo para calculcar total de vendas

    public double calcularTotalVenda() {
        double total = 0.0;

        for (ItemVenda item : itens) {
            total += item.TotalItem();
        }

        return total - desconto;
    }

}
