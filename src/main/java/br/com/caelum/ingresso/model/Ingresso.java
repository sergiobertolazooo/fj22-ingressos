package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import javax.persistence.*;

import br.com.caelum.ingresso.descontos.Desconto;

@Entity
public class Ingresso {
	
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    private Sessao sessao;
    
    private BigDecimal preco;
 
    public Ingresso() {
    	
    }    
    
    public Ingresso(Sessao sessao, Desconto desconto) {
    	this.sessao = sessao;
    	
    	//BigDecimal precoComDesconto = BigDecimal.ZERO;
    	
		/*
		 * switch(tipoDoIngresso) { case "Estudante": precoComDesconto =
		 * sessao.getPreco().multiply(BigDecimal.valueOf(0.5)); case "Correntista":
		 * precoComDesconto = sessao.getPreco().multiply(BigDecimal.valueOf(0.7)); case
		 * "Idoso": precoComDesconto =
		 * sessao.getPreco().multiply(BigDecimal.valueOf(0.6)); default:
		 * precoComDesconto = sessao.getPreco(); }
		 */
    	
    	//this.preco = precoComDesconto;
    	
    	this.preco = desconto.aplicaDesconto(sessao.getPreco());
    }
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
