package br.com.caelum.ingresso.model.form;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.enums.TipoDeIngresso;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;

public class CarrinhoForm {


	private List<Ingresso> ingressos = new ArrayList<>();
	
	public List<Ingresso> toIngressos(SessaoDao sessaoDao, LugarDao lugarDao){
		List<Ingresso> listaDeIngressoSelecionados = new ArrayList<>();
		
		for (Ingresso ingresso : ingressos) {
			Integer sessaoId = ingresso.getSessao().getId();
			Integer lugarId = ingresso.getLugar().getId();
			
			TipoDeIngresso tipoDeIngresso =  ingresso.getTipoDeIngresso();
			
			Sessao sessao = sessaoDao.findOne(sessaoId);
			Lugar lugar = lugarDao.findOne(lugarId);
			
			Ingresso novoIngresso = new Ingresso(sessao,tipoDeIngresso, lugar);
			
			listaDeIngressoSelecionados.add(novoIngresso);	
			
		}
		
		return listaDeIngressoSelecionados;
	}
	
	
	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

}
