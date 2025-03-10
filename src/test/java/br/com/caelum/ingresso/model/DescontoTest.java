package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime; 

import org.junit.Assert;
import org.junit.Test;
 
import br.com.caelum.ingresso.enums.TipoDeIngresso; 

public class DescontoTest {
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		 
		Filme filme = new Filme("Teste", Duration.ofMinutes(120), "Sci-fi", new BigDecimal(12.0));
		
		Sala sala = new Sala("sala sci-fi", new BigDecimal(20.5));
		
		LocalTime horario = LocalTime.parse("10:00:00");
		
		Sessao sessao = new Sessao(horario, sala, filme);
		
		Lugar lugar = new Lugar("C",4);
		
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, lugar);
		
		BigDecimal precoEsperado = new BigDecimal(32.5);
		 
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}

}
