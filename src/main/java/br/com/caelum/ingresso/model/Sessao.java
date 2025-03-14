package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany; 

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
    
    private BigDecimal preco = BigDecimal.ZERO;
    
    @OneToMany(mappedBy = "sessao", fetch = FetchType.EAGER)
    private Set<Ingresso> ingressos = new HashSet<Ingresso>();
    
    /**
     * @deprecated Hibernate only
     */
	public Sessao() {
		
	}
	
	public Sessao(LocalTime horario, Sala sala, Filme filme) {
		this.horario = horario;
		this.sala = sala;
		this.filme = filme;
		this.preco = sala.getPreco().add(filme.getPreco());
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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Map<String, List<Lugar>> getMapaDeLugares() {
		return this.sala.getMapaDeLugares();
	}

	public Set<Ingresso> getIngressos(){
		return ingressos;
	}
	
	public void setIngressos(Set<Ingresso> ingressos){
		this.ingressos = ingressos;
	}
		
	public boolean lugarIsDIsponivel(Lugar lugarEscolhido) {
		
		for (Ingresso ingresso : this.ingressos) {
			Lugar lugarOculpado = ingresso.getLugar();
			
			if(lugarEscolhido.equals(lugarOculpado)) {
				return false;
			}
		}
		 
		return true;
		//return ingressos.stream().forEach(ingresso -> {ingresso.getLugar().equals(lugarEscolhido) );
	}
	
}
