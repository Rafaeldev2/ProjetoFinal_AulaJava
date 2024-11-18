package cafeteria.vendas.produtos;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private String unidadeMedida;


    public Produto(int id, String nome, double preco, String unidadeMedida) {
        this.id =id;
        this.nome = nome;
        this.preco = preco;
        this.unidadeMedida = unidadeMedida;
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

    public String getUnidadeMedida() {
        return unidadeMedida;
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

    public void  setUnidadeMedida( String unidadeMedida){
        this.unidadeMedida = unidadeMedida;

    }


    // @Override
    // public  String toString(){
        // return  nome;    // Para exibição no JComboBox
    // }


}


