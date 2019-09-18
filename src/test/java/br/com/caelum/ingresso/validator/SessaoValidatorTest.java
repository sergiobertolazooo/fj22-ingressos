package br.com.caelum.ingresso.validator;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
 

import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.junit.Assert;

public class SessaoValidatorTest {

	@Test
	public void deveAdicionarSeListaVazia() {
		List<Sessao> sessoes = Collections.emptyList();
		
		SessaoValidator ssv = new SessaoValidator(sessoes);
		
		Filme filme = new Filme("Teste", Duration.ofMinutes(120), "Sci-fi", BigDecimal.ZERO);
		
		Sala sala = new Sala("sala sci-fi",BigDecimal.ZERO);
		
		LocalTime horario = LocalTime.parse("10:00:00");
		
		Sessao sessao = new Sessao(horario, sala, filme);
		
		boolean cabe = ssv.validaHorarios(sessao);
		
		Assert.assertTrue(cabe);
	}
 

}
