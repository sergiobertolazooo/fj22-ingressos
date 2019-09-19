package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.CompraDao;
import br.com.caelum.ingresso.dao.LugarDao; 
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Cartao;
import br.com.caelum.ingresso.model.Compra;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.form.CarrinhoForm;

@Controller
public class CompraController {
 	
	@Autowired
	private LugarDao ld;
	
	@Autowired
	private SessaoDao ssd;
	
	@Autowired
	private CompraDao cd;
	
	@Autowired
	private Carrinho carrinho;

	@PostMapping("/compra/ingressos")
	public ModelAndView prepraraCheckout(CarrinhoForm carrinhoForm) {
		
		ModelAndView mav = new ModelAndView("redirect:/compra"); //O "redirect:" é utilizado para chamar outra rota
		
		List<Ingresso> listaDeIngressoSelecionados = carrinhoForm.toIngressos(ssd, ld);
		
		listaDeIngressoSelecionados.stream().forEach(carrinho::add);
 
		return mav;
	}
	
	@GetMapping("/compra")
	public ModelAndView checkout(Cartao cartao) {
		
		ModelAndView mav = new ModelAndView("compra/pagamento");
		mav.addObject("carrinho", carrinho);
		return mav;
	}
	
	@PostMapping("/compra/comprar")
	@Transactional
	public ModelAndView comprar(@Valid Cartao cartao, BindingResult result) {
		 
		ModelAndView mav = new ModelAndView("redirect:/");
		
		if(cartao.isValido()) {
			cd.save(carrinho.toCompra());
			this.carrinho.limpa();
		}else {
			result.reject("Cartão invalido", "Cartão invalido");
			return checkout(cartao);
		}

		
		return mav; 
	}
	
}
