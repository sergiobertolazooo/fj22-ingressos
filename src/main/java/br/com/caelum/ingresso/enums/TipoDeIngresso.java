package br.com.caelum.ingresso.enums;

import java.math.BigDecimal;

import br.com.caelum.ingresso.descontos.*;

public enum TipoDeIngresso {
	
	INTEIRO(new SemDesconto()),
	ESTUDANTE(new DescontoEstudante()),
	BANCO(new DescontoCorrentista());
	
	private final Desconto desconto;

	private TipoDeIngresso(Desconto desconto) {
		this.desconto = desconto;
	}
	
	public BigDecimal aplicaDesconto(BigDecimal preco) {
		return desconto.aplicaDesconto(preco);
	}
	
	public String getDescricao() {
		return desconto.getDescricao();
	}

}
