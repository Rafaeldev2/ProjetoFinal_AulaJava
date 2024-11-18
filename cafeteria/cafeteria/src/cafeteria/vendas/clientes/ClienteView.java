package cafeteria.vendas.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
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
				onClickSalvar();
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
	 * Executa as tarefas para efetuar uma pesquisa com base no ID informado
	 */
	protected void onClickPesquisar() {
		// TODO: Implementar
		System.out.println("==> onClickPesquisar");
	}

	/**
	 * Executa as tarefas para preparar a interface para a inclusão de um novo
	 * cliente
	 */
	protected void onClickIncluirNovoCliente() {
		// TODO: Implementar
		System.out.println("==> onClickIncluirNovoCliente");
	}

	/**
	 * Executa as tarefas para voltar a inclusão de um cliente
	 */
	protected void onClickVoltar() {
		// TODO: Implementar
		System.out.println("==> onClickVoltar");
	}

	/**
	 * Executa as tarefas para salvar a inclusão de um novo cliente
	 */
	protected void onClickSalvar() {
		// TODO: Implementar
		System.out.println("==> onClickSalvar");
	}

}
