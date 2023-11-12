package br.com.fiap.fintech.enums;

public enum Categoria {
	
	SALARIO,
	BONUS,
	PRESENTE,
	ALUGUEL,
	LUZ,
	AGUA,
	INTERNET,
	FATURA_CARTAO,
	APORTE;
	
	public boolean isRecebimento(Categoria categoria) {
		return categoria == Categoria.SALARIO
				|| categoria == Categoria.BONUS
				|| categoria == Categoria.PRESENTE;
	}
	
	public boolean isDespesa(Categoria categoria) {
		return categoria == Categoria.AGUA
				|| categoria == Categoria.LUZ
				|| categoria == Categoria.INTERNET
				|| categoria == Categoria.FATURA_CARTAO
				|| categoria == Categoria.ALUGUEL;
	}
	
	public boolean isInvestimento(Categoria categoria) {
		return categoria == Categoria.APORTE;
	}
}
