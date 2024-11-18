package cafeteria.vendas.relatorios;

import java.io.File;

public interface RelatorioExportavelEmArquivoTexto {

	String getNomeRelatorio();

	void exportar(File destino);
}
