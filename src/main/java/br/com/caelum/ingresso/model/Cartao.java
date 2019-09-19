package br.com.caelum.ingresso.model;

import java.time.YearMonth;

public class Cartao {

	private String numero;
	private Integer cvv;
	private YearMonth vencimento;
	
	public Cartao() {
		
	}

	public Cartao(String numero, Integer cvv, YearMonth vencimento) {
		super();
		this.numero = numero;
		this.cvv = cvv;
		this.vencimento = vencimento;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Integer getCvv() {
		return cvv;
	}
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	public YearMonth getVencimento() {
		return vencimento;
	}
	public void setVencimento(YearMonth vencimento) {
		this.vencimento = vencimento;
	}
	
	public boolean isValido() {
		return vencimento.isAfter(YearMonth.now());
	}
	
	
}
