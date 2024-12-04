package cafeteria.vendas;

import cafeteria.connectionSQL.DatabaseConnection;
import cafeteria.vendas.clientes.Cliente;
import cafeteria.vendas.clientes.IClienteService;
import cafeteria.vendas.produtos.EstoqueProduto;
import cafeteria.vendas.produtos.IProdutoService;
import cafeteria.vendas.produtos.Produto;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class VendaView extends JInternalFrame {

	private static final String TITULO = "Registro de Venda";

	private static final int POSICAO_X_INICIAL = 120;
	private static final int POSICAO_Y_INICIAL = 120;

	private static final int LARGURA = 750;
	private static final int ALTURA = 675;

	private static final int COLUNA_SELECAO = 0;
	private static final int COLUNA_NOME = 1;
	private static final int COLUNA_VALOR_UNITARIO = 2;
	private static final int COLUNA_QUANTIDADE = 3;
	private static final int COLUNA_VALOR_TOTAL = 4;

	private static final long serialVersionUID = 1L;

	private JTextField id;
	private JTextField idCliente;
	private JFormattedTextField dataHora;
	private JTextField nomeCliente;
	private JComboBox<Produto> produto;
	private JFormattedTextField quantidade;
	private JFormattedTextField desconto;
	private JFormattedTextField totalVenda;
	private JTextField medida;


	private JTable table;
	private DefaultTableModel model;

	private JButton btConfirmar;
	private JButton btCancelar;
	private JButton btBuscarCliente;
	private JButton btAdicionarItem;
	private JButton btRemoverItensSelecionados;

	private IVendaService vendaService = null;
	private IClienteService clienteService = null;
	private IProdutoService produtoService = null;



	private List<ItemVenda> itens;

	/**
	 * Cria a janela para inclusão de uma venda
	 */
	public VendaView(IVendaService vendaService, IClienteService clienteService, IProdutoService produtoService) {
		this.vendaService = vendaService;
		this.clienteService = clienteService;
		this.produtoService = produtoService;
		this.itens = new ArrayList<>();

		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(2);

		setClosable(true);
		setIconifiable(true);
		setSize(LARGURA, ALTURA);
		setLocation(POSICAO_X_INICIAL, POSICAO_Y_INICIAL);
		setTitle(TITULO);
		getContentPane().setLayout(null);

		JLabel lbId = new JLabel("Cliente ID:");
		lbId.setBounds(31, 40, 72, 17);
		getContentPane().add(lbId);

		id = new JTextField();
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(109, 38, 114, 21);
		getContentPane().add(id);
		id.setColumns(10);

		JLabel lbNome = new JLabel("Nome:");
		lbNome.setBounds(31, 73, 60, 17);
		getContentPane().add(lbNome);

		nomeCliente = new JTextField();
		nomeCliente.setBounds(109, 71, 600, 21);
		getContentPane().add(nomeCliente);
		nomeCliente.setColumns(10);
		nomeCliente.setEditable(false);

		JLabel lbProduto = new JLabel("Produto:");
		lbProduto.setBounds(31, 106, 60, 17);
		getContentPane().add(lbProduto);

		// Carregar produtos
		List<Produto> produtos = produtoService.listarProdutos(DatabaseConnection.getConnection());
		if (produtos != null && !produtos.isEmpty()) {
			produto = new JComboBox<>(produtos.toArray(new Produto[0]));
			produto.setBounds(109, 104, 600, 21);
			getContentPane().add(produto);
	
			produto.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent event) {
					if (event.getStateChange() == ItemEvent.SELECTED) {
						Produto produtoSelecionado = (Produto) event.getItem();
						medida.setText(produtoSelecionado.getMedida().toString()); // Atualiza a medida
						quantidade.setValue(0); // Reseta a quantidade para 0 ao selecionar um novo produto
					}
				}
			});
		} else {
			JOptionPane.showMessageDialog(this, "Nenhum produto encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}

		btConfirmar = new JButton("Registrar Venda");
		btConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickRegistrarVenda();
			}
		});
		btConfirmar.setBounds(558, 585, 151, 27);
		getContentPane().add(btConfirmar);

		btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickCancelar();
			}
		});
		btCancelar.setBounds(441, 585, 105, 27);
		getContentPane().add(btCancelar);

		btBuscarCliente = new JButton("Buscar Cliente");
		btBuscarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickPesquisarCliente();
			}
		});
		btBuscarCliente.setBounds(235, 35, 120, 27);
		getContentPane().add(btBuscarCliente);

		JLabel lbMedida = new JLabel("Medida:");
		lbMedida.setBounds(31, 139, 60, 17);
		getContentPane().add(lbMedida);

		JLabel lbQuantidade = new JLabel("Quantidade:");
		lbQuantidade.setBounds(31, 172, 79, 17);
		getContentPane().add(lbQuantidade);

		medida = new JTextField();
		medida.setBounds(109, 137, 114, 21);
		medida.setEditable(false);
		getContentPane().add(medida);
		medida.setColumns(10);

		NumberFormat integerFormat = NumberFormat.getIntegerInstance();
		quantidade = new JFormattedTextField(integerFormat);
		quantidade.setHorizontalAlignment(SwingConstants.RIGHT);
		quantidade.setBounds(109, 170, 114, 21);
		quantidade.setEditable(false);
		getContentPane().add(quantidade);
		quantidade.setColumns(10);

		JLabel lbDesconto = new JLabel("Desconto:");
		lbDesconto.setBounds(31, 534, 60, 17);
		getContentPane().add(lbDesconto);

		desconto = new JFormattedTextField(numberFormat);
		desconto.setHorizontalAlignment(SwingConstants.RIGHT);
		desconto.setBounds(109, 529, 114, 21);
		getContentPane().add(desconto);
		desconto.setColumns(10);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(31, 563, 60, 17);
		getContentPane().add(lblTotal);

		totalVenda = new JFormattedTextField(numberFormat);
		totalVenda.setHorizontalAlignment(SwingConstants.RIGHT);
		totalVenda.setBounds(109, 561, 114, 21);
		getContentPane().add(totalVenda);
		totalVenda.setColumns(10);
		totalVenda.setEditable(false);

		btAdicionarItem = new JButton("Adicionar Produto");
		btAdicionarItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onClickAdicionarItemVenda();
			}
		});
		btAdicionarItem.setBounds(507, 203, 202, 27);
		getContentPane().add(btAdicionarItem);

		btRemoverItensSelecionados = new JButton("Remover Itens Selecionados");
		btRemoverItensSelecionados.setBounds(507, 516, 202, 27);
		btRemoverItensSelecionados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickRemoverItensSelecionados();
			}
		});
		getContentPane().add(btRemoverItensSelecionados);

		// Criação da tabela
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		model = new DefaultTableModel(new Object[] { "", "Produto", "Preço", "Quantidade", "Total" }, 0) {
			private static final long serialVersionUID = -213504134060145107L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == COLUNA_SELECAO) {
					return Boolean.class;
				} else if (columnIndex == COLUNA_VALOR_UNITARIO || columnIndex == COLUNA_VALOR_TOTAL) {
					return Double.class;
				} else if (columnIndex == COLUNA_QUANTIDADE) {
					return Integer.class;
				}
				return super.getColumnClass(columnIndex);
			}
		};

		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(480, 250));
		table.setFillsViewportHeight(true);

		// Formatação das colunas
		table.getColumnModel().getColumn(COLUNA_SELECAO).setMaxWidth(40);
		table.getColumnModel().getColumn(COLUNA_NOME).setPreferredWidth(10);
		table.getColumnModel().getColumn(COLUNA_VALOR_UNITARIO).setCellRenderer(new NumberRenderer());
		table.getColumnModel().getColumn(COLUNA_VALOR_UNITARIO).setMaxWidth(75);
		table.getColumnModel().getColumn(COLUNA_QUANTIDADE).setCellRenderer(new NumberRenderer());
		table.getColumnModel().getColumn(COLUNA_QUANTIDADE).setMaxWidth(75);
		table.getColumnModel().getColumn(COLUNA_VALOR_TOTAL).setCellRenderer(new NumberRenderer());
		table.getColumnModel().getColumn(COLUNA_VALOR_TOTAL).setMaxWidth(75);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		// Adicionando a JTable em um JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.setBounds(31, 242, 678, 262);
		getContentPane().add(panel);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int column = table.columnAtPoint(e.getPoint());

				// Verifica se a célula clicada é da coluna de seleção
				if (column == COLUNA_SELECAO) {
					boolean currentValue = (Boolean) table.getValueAt(row, column);
					// Troca o valor do JCheckBox
					table.setValueAt(!currentValue, row, column);
				}
			}
		});

		desconto.addPropertyChangeListener("value", evt -> atualizarTotalVenda());
	}

	protected void onClickRemoverItensSelecionados() {
		List<Integer> linhasSelecionadas = new ArrayList<>();
		TableModel model = table.getModel();
		for (int linha = 0, qtdLinhas = model.getRowCount(); linha < qtdLinhas; linha++) {
			if ((Boolean) model.getValueAt(linha, COLUNA_SELECAO)) {
				linhasSelecionadas.add(linha);
			}
		}

		Collections.reverse(linhasSelecionadas);
		for (int linha : linhasSelecionadas) {
			((DefaultTableModel) model).removeRow(linha);
			this.itens.remove(linha);
		}
	}

	// private void carregarProdutos() {
    //     List<Produto> produtos = produtoService.listarProdutos(DatabaseConnection.getConnection());
    //     produto.removeAllItems(); // Limpa os itens existentes
    //     for (Produto p : produtos) {
    //         produto.addItem(p); // Adiciona cada produto ao JComboBox
    //     }
	// 	if (!produtos.isEmpty()) {
	// 		Produto primeiroProduto = produtos.get(0); // Obtém o primeiro produto
	// 		produto.setSelectedItem(primeiroProduto); // Seleciona o primeiro produto no JComboBox
	// 		medida.setText(primeiroProduto.getMedida().toString()); // Define a medida do primeiro produto
	// 	}
    // }

	/**
	 * Prepara o frame para a ação de registrar uma venda
	 */
	public void setupRegistrarNovaVenda() {
		// configura os botões de ação
		btConfirmar.setEnabled(false);
		btCancelar.setEnabled(false);
		btBuscarCliente.setEnabled(true);
		btAdicionarItem.setEnabled(false);
		btRemoverItensSelecionados.setEnabled(false);

		// configura o comportamento dos campos
		id.setEditable(true);
		produto.setEnabled(false);
		quantidade.setEditable(false);
		desconto.setEditable(false);
	}

	/**
	 * Executa as tarefas para efetuar uma pesquisa com base no ID cliente informado
	 * 
	 */
	protected void onClickPesquisarCliente() {
		String clienteIdStr = id.getText().trim();
		if (!clienteIdStr.isEmpty()) {
			try {
				int clienteId = Integer.parseInt(clienteIdStr);
				Cliente cliente = clienteService.pesquisarCliente(clienteId, DatabaseConnection.getConnection());
				if (cliente != null) {
					nomeCliente.setText(cliente.getNome());
					produto.setEnabled(true);
					quantidade.setEditable(true);
					desconto.setEditable(true);
					btAdicionarItem.setEnabled(true);
					btRemoverItensSelecionados.setEnabled(true);
					btConfirmar.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro!", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "ID de cliente inválido.", "Erro!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	protected void onClickCancelar() {
		id.setText("");
		nomeCliente.setText("");
		produto.setSelectedIndex(-1);
		medida.setText("");
		quantidade.setValue(0);
		desconto.setValue(0);
		totalVenda.setValue(0);
		model.setRowCount(0);
		itens.clear();
		setupRegistrarNovaVenda();
	}

	/**
	 * Executa as tarefas para registrar uma venda
	 */
	protected void onClickRegistrarVenda() {
		if (itens.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Adicione ao menos um produto à venda.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			Cliente cliente = clienteService.pesquisarCliente(Integer.parseInt(id.getText().trim()), DatabaseConnection.getConnection());
			Venda venda = new Venda(0, new Timestamp(System.currentTimeMillis()), cliente, itens, ((Number) desconto.getValue()).doubleValue(), 0.0);
			double totalVenda = venda.calcularTotalVenda();
			venda.setTotal(totalVenda);

			vendaService.criarVenda(venda, DatabaseConnection.getConnection());
			JOptionPane.showMessageDialog(this, "Venda registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			onClickCancelar(); // Limpa a tela após o registro
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Erro no registro da venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	protected void onClickAdicionarItemVenda() {
		Produto produtoSelecionado = (Produto) produto.getSelectedItem();
		int qtd = ((Number) quantidade.getValue()).intValue();
	
		if (produtoSelecionado == null || qtd <= 0) {
			JOptionPane.showMessageDialog(this, "Selecione um produto e insira uma quantidade válida.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Verifica se o produto tem estoque suficiente
		if (produtoSelecionado instanceof EstoqueProduto estoqueProduto) {
			if(estoqueProduto.getEstoque() < qtd){
				JOptionPane.showMessageDialog(this, 
					"Estoque insuficiente para o produto selecionado. Estoque disponível: " + estoqueProduto.getEstoque(),
					"Aviso",
					JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	
		double valorUnitario = produtoSelecionado.getPreco();
		double valorTotal = valorUnitario * qtd;
	
		ItemVenda item = new ItemVenda(0, produtoSelecionado.getNome(), produtoSelecionado.getMedida(), qtd, valorUnitario);
    	itens.add(item);
	
		model.addRow(new Object[] { false, produtoSelecionado.getNome(), valorUnitario, qtd, valorTotal });
		atualizarTotalVenda();
	}

	private void atualizarTotalVenda() {
		double total = itens.stream().mapToDouble(ItemVenda::TotalItem).sum();
		
		// Verifica se o desconto é null e define como 0 se for
		double descontoValor = desconto.getValue() != null ? ((Number) desconto.getValue()).doubleValue() : 0.0;
		
		totalVenda.setValue(total - descontoValor);
	}

	// Classe para renderizar valores numéricos formatados
	class NumberRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = -3093220843229636461L;

		private final NumberFormat formatter;

		public NumberRenderer() {
			setHorizontalAlignment(SwingConstants.RIGHT);
			formatter = NumberFormat.getInstance();
			formatter.setMinimumFractionDigits(2);
			formatter.setMaximumFractionDigits(2);
			formatter.setGroupingUsed(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {
			if (value instanceof Double) {
				value = formatter.format(value);
			}
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		}
	}

	// Renderer para centralizar texto
	class CenterRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = -6091171430025834311L;

		public CenterRenderer() {
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

}
