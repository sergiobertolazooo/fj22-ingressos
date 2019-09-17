package br.com.caelum.ingresso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;

@Controller
public class SessaoController {
	
	@Autowired
	private FilmeDao fd;
	
	@Autowired
	private SalaDao sd;
 
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId) {
				
		List<Filme> filmes = fd.findAll();
	 		
		ModelAndView mav = new  ModelAndView("sessao/sessao");
		
		mav.addObject("filmes", filmes);
		
		mav.addObject("sala", sd.findOne(salaId));
		
		return mav;
	}
	
	//modo antigo...
	public String form(Model model) {
		return "uma-jsp.jsp";
	}
}
