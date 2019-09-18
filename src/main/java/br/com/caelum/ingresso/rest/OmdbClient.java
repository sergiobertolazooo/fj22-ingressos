package br.com.caelum.ingresso.rest;
  
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;

public class OmdbClient {
	
	public DetalhesDoFilme getDetalhesFilme(Filme filme) {
		
		
		DetalhesDoFilme detalhesDoFilme = null;
		
		  String nomeDoFilme = filme.getNome();
	        
	        nomeDoFilme = nomeDoFilme.replace(" ", "+");
	        
	        String urlAPI = "https://omdb-fj22.herokuapp.com/movie?title=%s"; 
	        
	        urlAPI = String.format(urlAPI, nomeDoFilme); 

	        RestTemplate rest = new RestTemplate();
	        
	        try {
  
	        	detalhesDoFilme = rest.getForObject(urlAPI, DetalhesDoFilme.class);
	        	
	        }catch(RestClientException e) {
	        	
	        	e.printStackTrace();
	        	detalhesDoFilme = new DetalhesDoFilme();
	        	detalhesDoFilme.setTitulo(filme.getNome());
	        	detalhesDoFilme.setDescricao("Sem maiores informações");
	        	
	        }
	        
	    	
		return detalhesDoFilme ;
	}

}
