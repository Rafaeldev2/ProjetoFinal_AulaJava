package cafeteria.vendas.produtos;

import cafeteria.connectionSQL.DatabaseConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ProdutoView extends JInternalFrame {

	private static final String TITULO = "Cadastro de Produto";

	private static final int POSICAO_X_INICIAL = 60;
	private static final int POSICAO_Y_INICIAL = 60;

	private static final int LARGURA = 580;
	private static final int ALTURA = 300;

	private static final long serialVersionUID = 1L;

	private JTextField id;
	private JTextField nome;
	private JComboBox<UnidadeMedida> medida;
	private JFormattedTextField preco;
	private JTextField estoque;
	private JCheckBox temEstoque;

	private JButton btSalvar;
	private JButton btVoltar;
	private JButton btNovoProduto;
	private JButton btPesquisar;

	private IProdutoService service = null;

	/**
	 * Cria a janela do CRUD do produto
	 */
	private boolean isUpdating = false;
	public ProdutoView(IProdutoService service) {
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
		id.setBounds(109, 36, 114, 27);
		getContentPane().add(id);
		id.setColumns(10);

		JLabel lbNome = new JLabel("Nome:");
		lbNome.setBounds(31, 73, 60, 17);
		getContentPane().add(lbNome);

		nome = new JTextField();
		nome.setBounds(109, 71, 430, 29);
		getContentPane().add(nome);
		nome.setColumns(10);

		JLabel lbMedida = new JLabel("Medida:");
		lbMedida.setBounds(31, 106, 60, 17);
		getContentPane().add(lbMedida);

        medida = new JComboBox<>(UnidadeMedida.values());
		medida.setBounds(109, 104, 114, 21);
		getContentPane().add(medida);

		btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isUpdating) {
					onClickAtualizar();
				} else {
					onClickSalvar();
				}
			}
		});
    

		btSalvar.setBounds(434, 229, 105, 27);
		getContentPane().add(btSalvar);

		btVoltar = new JButton("Voltar");
		btVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickVoltar();
			}
		});
		btVoltar.setBounds(322, 229, 105, 27);
		getContentPane().add(btVoltar);

		btNovoProduto = new JButton("Novo Produto");
		btNovoProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickIncluirNovoProduto();
			}
		});
		btNovoProduto.setBounds(400, 35, 139, 27);
		getContentPane().add(btNovoProduto);

		btPesquisar = new JButton("Pesquisar");
		btPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickPesquisar();
			}
		});
		btPesquisar.setBounds(235, 35, 96, 27);
		getContentPane().add(btPesquisar);

		JLabel lbPreco = new JLabel("Preço:");
		lbPreco.setBounds(31, 136, 60, 19);
		getContentPane().add(lbPreco);

		JLabel lbEstoque = new JLabel("Estoque:");
		lbEstoque.setBounds(31, 205, 60, 19);
		getContentPane().add(lbEstoque);

		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(2);
		preco = new JFormattedTextField(numberFormat);
		preco.setHorizontalAlignment(SwingConstants.RIGHT);
		preco.setBounds(109, 137, 114, 25);
		getContentPane().add(preco);
		preco.setColumns(10);

		estoque = new JTextField();
		estoque.setHorizontalAlignment(SwingConstants.RIGHT);
		estoque.setBounds(109, 203, 114, 26);
		getContentPane().add(estoque);
		estoque.setColumns(10);

		temEstoque = new JCheckBox("Tem estoque?");
		temEstoque.setBounds(26, 166, 114, 25);
		temEstoque.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (temEstoque.isSelected()) {
					estoque.setEnabled(true); // Habilita o campo de estoque
				} else {
					estoque.setEnabled(false); // Desabilita o campo de estoque
					estoque.setText(""); // Limpa o campo de estoque
				}
			}
		});
		getContentPane().add(temEstoque);	}

	/**
	 * Prepara o frame para a ação de consultar
	 */
	
	public void setupConsultar() {
		// configura os botões de ação
		btSalvar.setEnabled(false);
		btVoltar.setEnabled(false);
		btNovoProduto.setEnabled(true);
		btPesquisar.setEnabled(true);

		// configura o comportamento dos campos
		id.setEnabled(true);
		nome.setEnabled(false);
		medida.setEnabled(false);
		preco.setEnabled(false);
		temEstoque.setEnabled(false);
		estoque.setEnabled(false);
	}

	public void setupAtualizar() {
		// configura os botões de ação
		isUpdating = true;
		btSalvar.setText("Atualizar");
		btSalvar.setEnabled(true);
		btVoltar.setEnabled(true);
		btNovoProduto.setEnabled(true);
		btPesquisar.setEnabled(true);

		// configura o comportamento dos campos
		id.setEnabled(false);
		nome.setEnabled(true);
		medida.setEnabled(true);
		preco.setEnabled(true);
		temEstoque.setEnabled(true);
		estoque.setEnabled(true);
	}

	/**
	 * Executa as tarefas para efetuar uma pesquisa com base no ID informado
	 */
	protected void onClickPesquisar() {
		System.out.println(id.getText());
    try {
        Produto p = service.pesquisarProduto(Integer.parseInt(id.getText()), DatabaseConnection.getConnection());
        
        if (p != null) {
            nome.setText(p.getNome());
            preco.setText(String.valueOf(p.getPreco()));
            UnidadeMedida unidade = p.getMedida();
            medida.setSelectedItem(unidade);
            
			if (p instanceof EstoqueProduto estoqueProduto) {
                estoque.setText(String.valueOf(estoqueProduto.getEstoque()));
                temEstoque.setSelected(true);
				estoque.setEnabled(true);
            } else {
                estoque.setText("0");
                temEstoque.setSelected(false);
				estoque.setEnabled(false);
            }
            setupConsultar();
            btVoltar.setEnabled(true);
            btSalvar.setEnabled(true);
			id.setEnabled(false);
			setupAtualizar();
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.");
        }
        System.out.println("==> onClickPesquisar");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao pesquisar produto: " + e.getMessage());
		}
	}

	/**
	 * Executa as tarefas para preparar a interface para a inclusão de um novo
	 * produto
	 */
	protected void onClickIncluirNovoProduto() {
				
		btPesquisar.setEnabled(false);
		id.setEnabled(false);
		nome.setEnabled(true);
		preco.setEnabled(true);
		medida.setEnabled(true);
		temEstoque.setEnabled(true);
		btVoltar.setEnabled(true);
		btSalvar.setEnabled(true);
		System.out.println("==> onClickIncluirNovoProduto");
	}

	/**
	 * Executa as tarefas para voltar a inclusão de um produto
	 */
	protected void onClickVoltar() {
		
		System.out.println("==> onClickVoltar");
		this.dispose();
	}

	/**
	 * Executa as tarefas para salvar a inclusão de um novo produto
	 */
	protected void onClickSalvar() {
		if (isUpdating) {
			onClickAtualizar(); // Se estiver no modo de atualização, chama o método de atualização
			return; // Sai do método de salvar
		}

		UnidadeMedida medidaSelecionada = (UnidadeMedida) this.medida.getSelectedItem();
		String precoText = preco.getText().replace(",", ".");
		if (precoText.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O campo de preço não pode estar vazio. Por favor, insira um valor válido.");
			return; // Sai do método se o preço estiver vazio
		}

		double precoValue;
		try {
			precoValue = Double.parseDouble(precoText);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Preço inválido. Por favor, insira um número válido.");
			return; // Sai do método se o preço não for válido
		}

		Produto p;

		if (temEstoque.isSelected()) {
			int estoqueValue = Integer.parseInt(estoque.getText());
			p = new EstoqueProduto(0, nome.getText(), precoValue, medidaSelecionada, estoqueValue);
		} else {
			p = new Produto(0, nome.getText(), precoValue, medidaSelecionada);
		}
		service.criarProduto(p, DatabaseConnection.getConnection());
		JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
		
		id.setEnabled(false);
		nome.setEnabled(false);
		preco.setEnabled(false);
		estoque.setEnabled(false);
    	temEstoque.setSelected(false);
		btSalvar.setEnabled(false);
		btVoltar.setEnabled(true);
		btNovoProduto.setEnabled(true);
		btPesquisar.setEnabled(false);

		System.out.println("==> onClickSalvar");		
	}


	protected void onClickAtualizar() {
		UnidadeMedida medidaSelecionada = (UnidadeMedida) this.medida.getSelectedItem();
		String precoText = preco.getText().replace(",", ".");
		
		if (precoText.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O campo de preço não pode estar vazio. Por favor, insira um valor válido.");
			return; // Sai do método se o preço estiver vazio
		}
	
		double precoValue;
		try {
			precoValue = Double.parseDouble(precoText);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Preço inválido. Por favor, insira um número válido.");
			return; // Sai do método se o preço não for válido
		}
	
		// Verifica se estamos atualizando um produto existente
		if (id.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O campo ID não pode estar vazio.");
			return;
		}
		int idValue;
		try {
			idValue = Integer.parseInt(id.getText().trim());
			Produto p;
	
			if (temEstoque.isSelected()) {
				int estoqueValue = Integer.parseInt(estoque.getText());
				p = new EstoqueProduto(idValue, nome.getText(), precoValue, medidaSelecionada, estoqueValue);
			} else {
				p = new Produto(idValue, nome.getText(), precoValue, medidaSelecionada);
			}
	
			// Atualiza o produto existente
			service.atualizarProduto(p, DatabaseConnection.getConnection());
			JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
	
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número válido.");
		}
		System.out.println("==> onClickAtualizar");
	}
}
