package br.com.caelum.ingresso.descontos;

import java.math.BigDecimal;

public class DescontoCorrentista implements Desconto{

	@Override
	public BigDecimal aplicaDesconto(BigDecimal preco) {
		 
		System.out.println(preco);
		
		return preco.multiply(BigDecimal.valueOf(0.6));
	}

	@Override
	public String getDescricao() { 
		return "Correntista";
	}

}
