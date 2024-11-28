package cafeteria.vendas.clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteService implements IClienteService {

    private String selectSQL = "SELECT id, nome,telefone FROM cliente";
    String insertSQL = "INSERT INTO cliente(id, nome, telefone) VALUES(?, ?, ?, ?, ?)";
    private String updateSQL = "UPDATE cliente SET nome = ?, telefone = ? WHERE id = ?";

    @Override
    public void criarCliente(Cliente c, Connection conn) {
        PreparedStatement psInsert = null;
       
        try {
            psInsert = conn.prepareStatement(insertSQL);
            psInsert.setString(1, String.valueOf(c.getId()));
            psInsert.setString(2, String.valueOf(c.getNome()));
            psInsert.setString(3, String.valueOf(c.getTelefone()));
            psInsert.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("Erro na insercao: " + sqle.getMessage());
        } finally {
            try {
                psInsert.close();
            } catch (SQLException sqle) {
                System.err.println("Nao foi possivel finalizar o statement: " + sqle.getMessage());
            }
            psInsert = null;
        }

    }

    @Override
    public void atualizarCliente(Cliente c, Connection conn) {

        PreparedStatement psUpdate = null;
        try {
            psUpdate = conn.prepareStatement(updateSQL);
            psUpdate.setString(1, String.valueOf(c.getNome()));
            psUpdate.setString(2, String.valueOf(c.getTelefone()));
            psUpdate.setInt(3, c.getId());
            psUpdate.execute();
        } catch (SQLException sqle) {
            System.out.println("Erro na execucao da atualizacao: " + sqle.getMessage());
        } finally {
            try {
                psUpdate.close();
            } catch (SQLException sqle) {
                System.err.println("Nao foi possivel finalizar o statement: " + sqle.getMessage());
            }
            psUpdate = null;
        }
    }

    @Override
    public ArrayList<Cliente> listarClientes(Connection conn) {

        ArrayList<Cliente> clientes = new ArrayList<>();
        PreparedStatement psReads = null;
        try {
            psReads = conn.prepareStatement(selectSQL);
            ResultSet rs = psReads.executeQuery();
            while (rs.next()) {

                clientes.add(new Cliente(

                        // 0,
                        // insertSQL,
                        // insertSQL

                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone")));
            }
        } catch (SQLException sqle) {
            System.err.println("Erro na consulta: " + sqle.getMessage());
        } finally {
            try {
                psReads.close();
            } catch (SQLException sqle) {
                System.err.println("Nao foi possivel finalizar o statement: " + sqle.getMessage());
            }
            psReads = null;
        }
        return clientes;
    }

    @Override
    public Cliente pesquisarCliente(int id, Connection conn) {
        Cliente clientes = null;
        PreparedStatement psReads = null;
        try {
            psReads = conn.prepareStatement(selectSQL);
            ResultSet rs = psReads.executeQuery();
            if (rs.next()) {
                int cliente_id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");

                clientes = new Cliente(cliente_id, nome, telefone);
                return clientes;
            }
        } catch (SQLException sqle) {
            System.err.println("Erro na consulta: " + sqle.getMessage());
        } finally {
            try {
                psReads.close();
            } catch (SQLException sqle) {
                System.err.println("Nao foi possivel finalizar o statement: " + sqle.getMessage());
            }
            psReads = null;
        }
        return clientes;
    }

}
