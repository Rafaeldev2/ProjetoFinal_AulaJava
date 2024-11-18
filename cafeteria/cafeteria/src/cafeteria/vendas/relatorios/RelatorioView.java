package cafeteria.vendas.relatorios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RelatorioView extends JInternalFrame {

	private static final int POSICAO_X_INICIAL = 90;
	private static final int POSICAO_Y_INICIAL = 90;

	private static final int LARGURA = 580;
	private static final int ALTURA = 190;

	private static final long serialVersionUID = 1L;

	private JTextField nomeRelatorio;
	private JTextField destinoCaminhoAbsoluto;
	private File destinoSelecionado = null;

	private JButton btExportar;
	private JButton btCancelar;
	private JButton btSelecionarDestino;

	private RelatorioExportavelEmArquivoTexto exportador = null;

	/**
	 * Cria a janela para exportação do relatório
	 */
	public RelatorioView(RelatorioExportavelEmArquivoTexto exportador) {
		this.exportador = exportador;

		setClosable(true);
		setIconifiable(true);
		setSize(LARGURA, ALTURA);
		setLocation(POSICAO_X_INICIAL, POSICAO_Y_INICIAL);
		setTitle("Exportador de Relatório");
		getContentPane().setLayout(null);

		JLabel lbId = new JLabel("Relatório:");
		lbId.setBounds(31, 40, 60, 17);
		getContentPane().add(lbId);

		nomeRelatorio = new JTextField();
		nomeRelatorio.setBounds(109, 38, 430, 21);
		getContentPane().add(nomeRelatorio);
		nomeRelatorio.setColumns(10);
		nomeRelatorio.setEditable(false);
		// TODO: Descomentar a linha abaixo quando já existir um exportador criado na TelaPricipal
		//nomeRelatorio.setText(exportador.getNomeRelatorio());

		JLabel lbDestino = new JLabel("Destino:");
		lbDestino.setBounds(31, 73, 60, 17);
		getContentPane().add(lbDestino);

		destinoCaminhoAbsoluto = new JTextField();
		destinoCaminhoAbsoluto.setBounds(109, 71, 266, 21);
		getContentPane().add(destinoCaminhoAbsoluto);
		destinoCaminhoAbsoluto.setColumns(10);
		destinoCaminhoAbsoluto.setEditable(false);

		btExportar = new JButton("Exportar");
		btExportar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickExportar();
			}
		});
		btExportar.setBounds(434, 107, 105, 27);
		getContentPane().add(btExportar);
		btExportar.setEnabled(false);

		btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickCancelar();
			}
		});
		btCancelar.setBounds(316, 107, 105, 27);
		getContentPane().add(btCancelar);
		btCancelar.setEnabled(true);

		btSelecionarDestino = new JButton("Selecionar Destino");
		btSelecionarDestino.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickSelecionarDestino();
			}
		});
		btSelecionarDestino.setBounds(387, 68, 152, 27);
		getContentPane().add(btSelecionarDestino);
		btSelecionarDestino.setEnabled(true);
	}

	/**
	 * Executa as tarefas para efetuar uma pesquisa com base no ID informado
	 */
	protected void onClickSelecionarDestino() {
		// Criação do JFileChooser
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			// Obtendo o arquivo selecionado
			destinoSelecionado = fileChooser.getSelectedFile();
			destinoCaminhoAbsoluto.setText(destinoSelecionado.getAbsolutePath());
			btExportar.setEnabled(true);
		}
	}

	/**
	 * Executa as tarefas para cancelar a geração do relatório
	 */
	protected void onClickCancelar() {
		this.dispose();
	}

	/**
	 * Executa as tarefas para exportar a exportação do relatório
	 */
	protected void onClickExportar() {
		// TODO: Implementar
		System.out.println("==> onClickExportar");
	}

}
