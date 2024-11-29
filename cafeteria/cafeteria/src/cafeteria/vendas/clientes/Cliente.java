package cafeteria.vendas.clientes;

public class Cliente {

	private int id;
	private String nome;
	private String telefone;
	
	
	public Cliente(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

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

	
}
