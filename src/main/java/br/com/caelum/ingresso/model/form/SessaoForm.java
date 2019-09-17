package br.com.caelum.ingresso.model.form;

import java.time.LocalTime; 
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
  
public class SessaoForm {
	
	@NotNull
	private Integer salaId;
	@NotNull
	private Integer FilmeId; 
	
	@NotNull(message = "Horário invalido para sessão")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horario;
	
	public Integer getSalaId() {
		return salaId;
	}
	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}
	public Integer getFilmeId() {
		return FilmeId;
	}
	public void setFilmeId(Integer filmeId) {
		FilmeId = filmeId;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	
	@Override
	public String toString() {
		return "SessaoForm [salaId=" + salaId + ", FilmeId=" + FilmeId + ", horario=" + horario + "]";
	} 
	
	
    public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao) {
    	
    	Filme filme = filmeDao.findOne(this.getFilmeId());
    	Sala sala = salaDao.findOne(this.getSalaId());
    	Sessao sessao = new Sessao(this.getHorario(),sala,filme); 
    	//sessao.setId(this.id);
        return sessao;
    }
 	

}
