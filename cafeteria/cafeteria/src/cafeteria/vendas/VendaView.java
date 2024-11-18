package cafeteria.vendas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import cafeteria.vendas.clientes.IClienteService;
import cafeteria.vendas.produtos.IProdutoService;
import cafeteria.vendas.produtos.Produto;

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

		// TODO: Carregar uma lista dos produtos cadastrados
		List<Produto> produtos = new ArrayList<>();
		produto = new JComboBox<>(produtos.toArray(new Produto[0]));
		produto.setBounds(109, 104, 600, 21);
		getContentPane().add(produto);
		produto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					Produto produtoSelecionado = (Produto) event.getItem();
					// TODO: Completar o código abaixo para atualizar o valor da medida
					// medida.setText(produtoSelecionado);
				}
			}
		});

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
				onClickBuscarCliente();
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
		getContentPane().add(medida);
		medida.setColumns(10);
		medida.setEditable(false);

		NumberFormat integerFormat = NumberFormat.getIntegerInstance();
		quantidade = new JFormattedTextField(integerFormat);
		quantidade.setHorizontalAlignment(SwingConstants.RIGHT);
		quantidade.setBounds(109, 170, 114, 21);
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
	 */
	protected void onClickBuscarCliente() {
		// TODO: Implementar
		System.out.println("==> onClickBuscarCliente");
	}

	/**
	 * Executa as tarefas para cancelar a venda
	 */
	protected void onClickCancelar() {
		// TODO: Implementar
		System.out.println("==> onClickCancelar");
	}

	/**
	 * Executa as tarefas para registrar uma venda
	 */
	protected void onClickRegistrarVenda() {
		// TODO: Implementar
		System.out.println("==> onClickRegistrarVenda");
	}

	/**
	 * Executa as tarefas para registrar uma venda
	 */
	protected void onClickAdicionarItemVenda() {
		// TODO: Criar de fato uma venda
		ItemVenda venda = new ItemVenda();
		this.itens.add(venda);

		// TODO: Substituir as duas próximas linhas pela inclusão de fato da venda
		int id = this.model.getRowCount() + 1; // ID sequencial
		this.model.addRow(new Object[] { Boolean.FALSE, "Nome " + id, 1.25, 10, 12.5 }); // Adiciona uma nova linha
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
