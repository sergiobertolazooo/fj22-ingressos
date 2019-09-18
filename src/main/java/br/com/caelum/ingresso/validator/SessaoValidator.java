package br.com.caelum.ingresso.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
 
import br.com.caelum.ingresso.model.Sessao;

public class SessaoValidator {
 
	private List<Sessao> sessoes;
	
	public SessaoValidator(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}
	
	public boolean validaHorarios(Sessao sessao) {
 
		if(validaTerminoSessao(sessao)) {
			return false;
		}
		
		return sessoes.stream().noneMatch(sessaoExistente -> horarioIsConflitante(sessaoExistente, sessao));
	}
	
	public boolean validaTerminoSessao(Sessao sessao) {

		LocalDateTime terminoSessaoNova = getTerminoDaSessao(sessao);
		LocalDateTime ultimoSegundo = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		
		/*
		 * System.out.println("-verificando termino----");
		 * System.out.println(terminoSessaoNova.toString()); System.out.println(
		 * "----------------------------------------------------------");
		 * System.out.println(ultimoSegundo.toString()); System.out.println(
		 * "----------------------------------------------------------");
		 * System.out.println(terminoSessaoNova.isAfter(ultimoSegundo));
		 * System.out.println("-fim verificando termino----");
		 */
		
		return (terminoSessaoNova.isAfter(ultimoSegundo));
	}
	
	public LocalDateTime getTerminoDaSessao(Sessao sessao) {
		LocalDateTime inicioSessao = getInicioDaSessao(sessao);	
		inicioSessao = inicioSessao.plus(sessao.getFilme().getDuracao());		
 
		return inicioSessao;
	}
	
	public LocalDateTime getInicioDaSessao(Sessao sessao) {
		LocalDateTime inicioSessao = LocalDateTime.of(LocalDate.now(),  sessao.getHorario()); 
		return inicioSessao;		
	} 
	
	public boolean horarioIsConflitante(Sessao sessaoExistente, Sessao sessaoNova) {
		
		LocalDateTime inicioSessaoExistente = getInicioDaSessao(sessaoExistente);
		LocalDateTime terminoSessaoExistente = getTerminoDaSessao(sessaoExistente);
		LocalDateTime inicioSessaoNova = getInicioDaSessao(sessaoNova);
		LocalDateTime terminoSessaoNova = getTerminoDaSessao(sessaoNova);
		
		boolean sessaoNovaTerminaAntesDaExistente = terminoSessaoNova.isBefore(inicioSessaoExistente);
		boolean sessaoNovaComecaDepoisDaExistente = terminoSessaoExistente.isBefore(inicioSessaoNova);
		
		if(sessaoNovaTerminaAntesDaExistente || sessaoNovaComecaDepoisDaExistente) {
			return false;
		}		
		
		return true;
	}

}
