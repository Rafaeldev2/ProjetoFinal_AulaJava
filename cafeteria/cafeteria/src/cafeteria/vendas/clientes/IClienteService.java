package cafeteria.vendas.clientes;

import java.util.ArrayList;

public interface IClienteService {

    public Cliente pesquisarCliente(int id);
    public void criarCliente(Cliente c);
    public void atualizarCliente(Cliente c);
    public ArrayList<Cliente> listarClientes();

}