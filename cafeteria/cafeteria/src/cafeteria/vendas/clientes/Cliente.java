package cafeteria.vendas.clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cliente {

	private int id;
	private String nome;
	private String telefone;
	
	
	public Cliente(int id, String nome, String telefone) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

    public void criarCliente(ClienteService clienteService, Connection conn) {
        PreparedStatement psInsert = null;
    
        try {
            psInsert = conn.prepareStatement(clienteService.insertSQL);
            psInsert.setString(1, String.valueOf(getId()));
            psInsert.setString(2, String.valueOf(getNome()));
            psInsert.setString(3, String.valueOf(getTelefone()));
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
	
}
