package cafeteria.vendas;
import  java.util.List;


public class Venda {
    private int clienteId;
    private String nomeProduto;
    private String medida;
    private double total;
    private double  desconto;
    private List<ItemVenda> itens;

    public Venda(int clienteId,String nomeProtuto, String medida, double total, double desconto){
         this.clienteId = clienteId;
         this.nomeProduto = nomeProtuto;
         this.medida = medida;
         this.desconto = desconto;
         this.total = total;
    }


// gets

    public int getClienteId() {
        return clienteId;
    }

    public double getDesconto() {
        return desconto;
    }

    public String getMedida() {
        return medida;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public double getTotal() {
        return total;
    }


// sets
    public void setClientId( int clienteId){
        this.clienteId = clienteId;
    }

    public void setnomeProduto( String nomeProduto){
        this.nomeProduto = nomeProduto;
    }
    
    public void setMedida( String medida){
        this.medida = medida;
    }
    

    public void setDesconto( double desconto){
        this.desconto = desconto;
    }
    

    public void setTotal( double total){
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
