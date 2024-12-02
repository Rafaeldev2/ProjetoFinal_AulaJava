package cafeteria.vendas.produtos;

public enum UnidadeMedida {

	UNIDADE(1),
    LATA(2),
    LITRO(3),
    PACOTE(4),
    FATIA(5),
    GARRAFA(6);

    private final int codigo;

    // Construtor
    UnidadeMedida(int codigo) {
        this.codigo = codigo;
    }

    // Método para obter o código
    public int getCodigo() {
        return codigo;
    }

    // Método para obter a UnidadeMedida a partir do código
    public static UnidadeMedida fromCodigo(int codigo) {
        for (UnidadeMedida unidade : UnidadeMedida.values()) {
            if (unidade.getCodigo() == codigo) {
                return unidade;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}