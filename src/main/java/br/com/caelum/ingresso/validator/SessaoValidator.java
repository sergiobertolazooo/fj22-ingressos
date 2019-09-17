package br.com.caelum.ingresso.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sessao;

@Component
public class SessaoValidator {
	
	private SessaoDao sessaoDao;
	
	
	public boolean validaHorarios(Sessao sessao) {
	 
		List<Sessao> sessoes = sessaoDao.findAll();
		
		if(!validaTerminoSessao(sessao)) {
			return false;
		}
		
		return true;
	}
	
	public boolean validaTerminoSessao(Sessao sessao) {

		LocalDateTime terminoSessaoNova = getTerminoDeSessaoNova(sessao);
		LocalDateTime ultimoSegundo = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		
		return (terminoSessaoNova.isAfter(ultimoSegundo));
	}
	
	public LocalDateTime getTerminoDeSessaoNova(Sessao sessao) {
		LocalDateTime inicioSessao = LocalDateTime.of(LocalDate.now(),  sessao.getHorario()); 
		inicioSessao.plus(sessao.getFilme().getDuracao());
		
		return inicioSessao;
		
		
	}

}
