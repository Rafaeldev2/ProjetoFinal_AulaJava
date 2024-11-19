package cafeteria.vendas;
// import  java.util.List;


public interface IVendaService {

    public Venda pesquisarVenda(int id);
    public void criarVenda(Venda v);
    public void atualizarVenda(Venda v);
    public void listarVenda(Venda v);
} 