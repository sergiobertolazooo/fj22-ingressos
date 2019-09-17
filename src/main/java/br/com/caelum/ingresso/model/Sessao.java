package br.com.caelum.ingresso.model;

import java.time.LocalTime; 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne; 

@Entity
public class Sessao {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
 
	private LocalTime horario;
    
    @ManyToOne
	private Sala sala;
    
    @ManyToOne
	private Filme filme;	
	
    /**
     * @deprecated Hibernate only
     */
	public Sessao() {
		
	}
	
	public Sessao(LocalTime horario, Sala sala, Filme filme) {
		this.horario = horario;
		this.sala = sala;
		this.filme = filme;
	}
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	@Override
	public String toString() {
		return "Sessao [id=" + id + ", horario=" + horario + ", sala=" + sala + ", filme=" + filme + "]";
	}

}
