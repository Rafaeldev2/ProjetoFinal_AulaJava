package cafeteria.vendas.clientes;

import cafeteria.connectionSQL.DatabaseConnection;
import cafeteria.vendas.produtos.EstoqueProduto;
import cafeteria.vendas.produtos.Produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class ClienteView extends JInternalFrame {

	private static final String TITULO = "Cadastro de Cliente";

	private static final int POSICAO_X_INICIAL = 30;
	private static final int POSICAO_Y_INICIAL = 30;

	private static final int LARGURA = 580;
	private static final int ALTURA = 210;

	private static final long serialVersionUID = 1L;

	private JTextField id;
	private JTextField nome;
	private JFormattedTextField telefone;

	private JButton btSalvar;
	private JButton btVoltar;
	private JButton btNovoCliente;
	private JButton btPesquisar;

	private IClienteService service = null;

	/**
	 * Cria a janela do CRUD do cliente
	 */
	private boolean isUpdating = false;
	public ClienteView(IClienteService service) {
		this.service = service;

		setClosable(true);
		setIconifiable(true);
		setSize(LARGURA, ALTURA);
		setLocation(POSICAO_X_INICIAL, POSICAO_Y_INICIAL);
		setTitle(TITULO);
		getContentPane().setLayout(null);

		JLabel lbId = new JLabel("ID:");
		lbId.setBounds(31, 40, 60, 17);
		getContentPane().add(lbId);

		id = new JTextField();
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(109, 38, 114, 21);
		getContentPane().add(id);
		id.setColumns(10);

		JLabel lbNome = new JLabel("Nome:");
		lbNome.setBounds(31, 73, 60, 17);
		getContentPane().add(lbNome);

		nome = new JTextField();
		nome.setBounds(109, 71, 430, 21);
		getContentPane().add(nome);
		nome.setColumns(10);

		JLabel lbTelefone = new JLabel("Telefone:");
		lbTelefone.setBounds(31, 106, 60, 17);
		getContentPane().add(lbTelefone);

		MaskFormatter maskFormatter;
		try {
			maskFormatter = new MaskFormatter("(##) #####-####");
			maskFormatter.setPlaceholderCharacter('_'); // Caracter de espaço reservado
			telefone = new JFormattedTextField(maskFormatter);
			telefone.setBounds(109, 104, 132, 21);
			getContentPane().add(telefone);
			telefone.setColumns(10);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isUpdating) {
					//onClickAtualizar();
				} else {
					onClickSalvar();
				}
			}
		});
		btSalvar.setBounds(434, 126, 105, 27);
		getContentPane().add(btSalvar);

		btVoltar = new JButton("Voltar");
		btVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickVoltar();
			}
		});
		btVoltar.setBounds(317, 126, 105, 27);
		getContentPane().add(btVoltar);

		btNovoCliente = new JButton("Novo Cliente");
		btNovoCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickIncluirNovoCliente();
			}
		});
		btNovoCliente.setBounds(400, 35, 139, 27);
		getContentPane().add(btNovoCliente);

		btPesquisar = new JButton("Pesquisar");
		btPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickPesquisar();
			}
		});
		btPesquisar.setBounds(235, 35, 96, 27);
		getContentPane().add(btPesquisar);
	}

	/**
	 * Prepara o frame para a ação de consultar
	 */
	public void setupConsultar() {
		// configura os botões de ação
		btSalvar.setEnabled(false);
		btVoltar.setEnabled(false);
		btNovoCliente.setEnabled(true);
		btPesquisar.setEnabled(true);

		// configura o comportamento dos campos
		id.setEnabled(true);
		nome.setEnabled(false);
		telefone.setEnabled(false);
	}

	/**
	 * Prepara o frame para a ação de incluir
	 */
	public void setupAtualizar() {
		// configura os botões de ação
		isUpdating = true;
		btSalvar.setText("Atualizar");
		btSalvar.setEnabled(true);
		btVoltar.setEnabled(true);
		btNovoCliente.setEnabled(true);
		btPesquisar.setEnabled(true);

		// configura o comportamento dos campos
		id.setEnabled(false);
		nome.setEnabled(true);
		telefone.setEnabled(true);
	}


	/**
	 * Executa as tarefas para efetuar uma pesquisa com base no ID informado
	 */
	protected void onClickPesquisar() {	
		System.out.println(id.getText());
		Cliente c = service.pesquisarCliente(Integer.parseInt(id.getText()), DatabaseConnection.getConnection());
		nome.setText(c.getNome());
		telefone.setText(c.getTelefone());
		
		setupConsultar();
		
		btVoltar.setEnabled(true);
		btSalvar.setEnabled(true);
		id.setEnabled(false);
		setupAtualizar();
		System.out.println("==> onClickPesquisar");
	}


	protected void onClickIncluirNovoCliente() {

		btPesquisar.setEnabled(false);
		btNovoCliente.setEnabled(false);
		id.setEnabled(false);
		nome.setEnabled(true);
		telefone.setEnabled(true);
		btVoltar.setEnabled(true);
		btSalvar.setEnabled(true);
		System.out.println("==> onClickIncluirNovoCliente");
	}

	/**
	 * Executa as tarefas para voltar a inclusão de um cliente
	 */
	protected void onClickVoltar() {
		System.out.println("==> onClickVoltar");
		this.dispose();

	}

	/**
	 * Executa as tarefas para salvar a inclusão de um novo cliente
	 */
	protected void onClickSalvar() {
		String nomeText = nome.getText();
		if (nomeText.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O campo de nome não pode estar vazio. Por favor, insira um valor válido.");
			return; // Sai do método se o preço estiver vazio
		}
		String telefoneText = telefone.getText();
		if (telefoneText.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O campo de telefone não pode estar vazio. Por favor, insira um valor válido.");
			return; // Sai do método se o preço estiver vazio
		}

		Cliente c = new Cliente(nome.getText(), telefone.getText());
		service.criarCliente(c, DatabaseConnection.getConnection());
		JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso!");
		
		id.setEnabled(true);
		nome.setEnabled(false);
		telefone.setEnabled(false);
		btSalvar.setEnabled(false);
		btVoltar.setEnabled(false);
		btNovoCliente.setEnabled(true);
		btPesquisar.setEnabled(true);

		System.out.println("==> onClickSalvar");
	}
	protected void onClickAtualizar() {
		
		String nomeText = nome.getText();
		if (nomeText.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O campo de nome não pode estar vazio. Por favor, insira um valor válido.");
			return; // Sai do método se o preço estiver vazio
		}
		String telefoneText = telefone.getText();
		if (telefoneText.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O campo de telefone não pode estar vazio. Por favor, insira um valor válido.");
			return; // Sai do método se o preço estiver vazio
		}
		if (id.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O campo ID não pode estar vazio.");
			return;
		}	

		int idValue;
		try {
			idValue = Integer.parseInt(id.getText().trim());
			Cliente c = new Cliente(idValue, nome.getText(), telefone.getText());

			service.atualizarCliente(c, DatabaseConnection.getConnection());
			JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
	
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número válido.");
		}
		System.out.println("==> onClickAtualizar");
	}

}
