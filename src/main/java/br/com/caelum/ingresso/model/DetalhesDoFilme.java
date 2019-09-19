package br.com.caelum.ingresso.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetalhesDoFilme {


	@JsonProperty("Poster")
	private String imagem;
	
	@JsonProperty("Year")
	private String ano;
	
	@JsonProperty("Director")
	private String diretores;
	
	@JsonProperty("Writer")
	private String escritores;
	
	@JsonProperty("Title")
	private String titulo;
	
	@JsonProperty("Actors")
	private String atores;
	
	@JsonProperty("Plot")
	private String descricao;
	
	@JsonProperty("imdbRating")
	private Double avaliacao;
	
	public DetalhesDoFilme() {
		
	}

	public DetalhesDoFilme(String imagem, String ano, String diretores, String escritores, String titulo, String atores,
			String descricao, Double avaliacao) {
	 
		this.imagem = imagem;
		this.ano = ano;
		this.diretores = diretores;
		this.escritores = escritores;
		this.titulo = titulo;
		this.atores = atores;
		this.descricao = descricao;
		this.avaliacao = avaliacao;
	}
	
	public DetalhesDoFilme(String descricao) {
	 
		this.imagem = "https://www.termoparts.com.br/wp-content/uploads/2017/10/no-image.jpg";
 
		this.descricao = descricao; 
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getDiretores() {
		return diretores;
	}

	public void setDiretores(String diretores) {
		this.diretores = diretores;
	}

	public String getEscritores() {
		return escritores;
	}

	public void setEscritores(String escritores) {
		this.escritores = escritores;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAtores() {
		return atores;
	}

	public void setAtores(String atores) {
		this.atores = atores;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}
	
}