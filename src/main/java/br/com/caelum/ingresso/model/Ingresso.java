package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import javax.persistence.*;

import br.com.caelum.ingresso.enums.TipoDeIngresso;

@Entity
public class Ingresso {
	
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    private Sessao sessao;
    
    private BigDecimal preco; 
    
    @ManyToOne
    private Lugar lugar;
    
    @Enumerated(EnumType.STRING)
    private TipoDeIngresso tipoDeIngresso;
 
    public Ingresso() {
    	
    }    
    
    public Ingresso(Sessao sessao, TipoDeIngresso tipoDeIngresso, Lugar lugar) {
    	this.sessao = sessao;  
    	this.preco = tipoDeIngresso.aplicaDesconto(sessao.getPreco());
    	this.lugar = lugar;
    	this.setTipoDeIngresso(tipoDeIngresso);
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

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public TipoDeIngresso getTipoDeIngresso() {
		return tipoDeIngresso;
	}

	public void setTipoDeIngresso(TipoDeIngresso tipoDeIngresso) {
		this.tipoDeIngresso = tipoDeIngresso;
	}

}
