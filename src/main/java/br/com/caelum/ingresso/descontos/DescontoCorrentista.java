package br.com.caelum.ingresso.descontos;

import java.math.BigDecimal;

public class DescontoCorrentista implements Desconto{

	@Override
	public BigDecimal aplicaDesconto(BigDecimal preco) {
		 
		return preco.multiply(BigDecimal.valueOf(0.6));
	}

}
