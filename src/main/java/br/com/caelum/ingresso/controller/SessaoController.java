package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;

@Controller
public class SessaoController {
	
	@Autowired
	private FilmeDao fd;
	
	@Autowired
	private SalaDao sd;
	
	@Autowired
	private SessaoDao ssd;
 
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
	
		if(sessaoResult.hasErrors()) return form(sessaoForm.getSalaId(),sessaoForm);
		
		Sessao sessao = sessaoForm.toSessao(sd, fd);		

		System.out.println(sessao.toString());
		
		ssd.save(sessao);
		
	 
		return new ModelAndView("redirect:/admin/sala/" + sessaoForm.getSalaId() + "/sessoes");
	}
 
}
