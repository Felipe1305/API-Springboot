package com.serratec.apirestfull.DTO;

import java.io.Serializable;

import com.serratec.apirestfull.domain.Produto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String nome;
	private Double preco;
	private String descricao;
	private String urlImagem;
	
	
	public ProdutoDTO() {
		super();
	}
	
	
	public ProdutoDTO (Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getPreco();
		descricao = obj.getDescricao();
		urlImagem = obj.getUrlImagem();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getUrlImagem() {
		return urlImagem;
	}


	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	
	
}
