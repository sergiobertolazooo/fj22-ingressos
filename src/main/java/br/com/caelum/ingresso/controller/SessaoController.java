package br.com.caelum.ingresso.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.enums.TipoDeIngresso;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.ImagemCapa;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.rest.OmdbClient;
import br.com.caelum.ingresso.validator.SessaoValidator;

@Controller
public class SessaoController {
	
	@Autowired
	private FilmeDao fd;
	
	@Autowired
	private SalaDao sd;
	
	@Autowired
	private SessaoDao ssd;
	
	@Autowired
	private Carrinho carrinho;

	@GetMapping("/sessao/{sessaoId}/lugares")
	public ModelAndView lugares(@PathVariable("sessaoId") Integer sessaoId) {
	 
		ModelAndView mav = new  ModelAndView("sessao/lugares");
		
		Sessao sessao = ssd.findOne(sessaoId);
		
		mav.addObject(sessao);
		
		OmdbClient cliente = new OmdbClient();
		
		Optional<ImagemCapa> dds = cliente.getDetalhesFilme(sessao.getFilme(), ImagemCapa.class);
		
		mav.addObject("imagemCapa", dds.orElse(new ImagemCapa()));		
		
		TipoDeIngresso[] tiposDeIngresso = TipoDeIngresso.values();
		
		mav.addObject("tiposDeIngressos", tiposDeIngresso);
		mav.addObject("carrinho", carrinho);
	 
		return mav;
	}
 
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm sform) {
				
		List<Filme> filmes = fd.findAll();
	 		
		ModelAndView mav = new  ModelAndView("sessao/sessao");
		
		mav.addObject("filmes", filmes);
		
		mav.addObject("sala", sd.findOne(salaId));
		
		mav.addObject("form", sform);
		
		return mav;
	}
	
	@PostMapping("/admin/sessao")
	@Transactional
	public ModelAndView Salvar(@Valid SessaoForm sessaoForm, BindingResult sessaoResult) {
	
		if(sessaoResult.hasErrors()) {
			return form(sessaoForm.getSalaId(),sessaoForm);
		}
		
		Sessao sessao = sessaoForm.toSessao(sd, fd);		

		System.out.println(sessao.toString());
	 
		SessaoValidator ssv = new SessaoValidator(ssd.BuscaSessoesDaSala(sessao.getSala()));
		
		if(ssv.validaHorarios(sessao)) {
			
			ssd.save(sessao);
			return new ModelAndView("redirect:/admin/sala/" + sessaoForm.getSalaId() + "/sessoes");
		}
		
		sessaoResult.rejectValue("horario", "error.sessionConflict", "Horario indisponivel");
		
		return form(sessaoForm.getSalaId(),sessaoForm);
		
	}
 
}
