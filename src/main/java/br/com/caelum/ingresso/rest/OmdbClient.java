package br.com.caelum.ingresso.rest;
  
import java.util.Optional;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
 
import br.com.caelum.ingresso.model.Filme;

public class OmdbClient {
	
	public <T> Optional<T> getDetalhesFilme(Filme filme, Class<T> tClass) {
	 
		String nomeDoFilme = filme.getNome();
        
        nomeDoFilme = nomeDoFilme.replace(" ", "+");
        
        String urlAPI = "https://omdb-fj22.herokuapp.com/movie?title=%s"; 
	        
        urlAPI = String.format(urlAPI, nomeDoFilme); 

        RestTemplate rest = new RestTemplate();
	        
        try {
  
        	return Optional.of(rest.getForObject(urlAPI, tClass));
	        	
        }catch(RestClientException e) {
        	
        	return Optional.empty(); 
        	
        } 
	}

}
