package cafeteria.vendas.produtos;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private UnidadeMedida Medida;

    public Produto(int id, String nome, double preco, UnidadeMedida Medida){
        this.id =id;
        this.nome = nome;
        this.preco = preco;
        this.Medida = Medida;
    }

    // gets
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public UnidadeMedida getMedida() {
        return Medida;
    }

    // sets

    public void  setID( int id){
        this.id = id;
    }

    public void  setNome( String nome){
        this.nome = nome;
    }

    public void  setPreco( double preco){
        this.preco = preco;
    }

    public void  setUnidadeMedida( UnidadeMedida Medida){
        this.Medida = Medida;
    }

    @Override
    public String toString() {
        return nome;
    }

}


