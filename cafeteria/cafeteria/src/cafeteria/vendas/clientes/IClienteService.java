package cafeteria.vendas.clientes;

import java.sql.Connection;
import java.util.ArrayList;

public interface IClienteService {

    public Cliente pesquisarCliente(int id, Connection conn);
    public void criarCliente(Cliente c, Connection conn);
    public void atualizarCliente(Cliente c, Connection conn);
    public ArrayList<Cliente> listarClientes(Connection conn);

}