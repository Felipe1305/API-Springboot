package com.serratec.apirestfull.DTO;

import java.io.Serializable;

import com.serratec.apirestfull.domain.Estado;

public class EstadoDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public EstadoDTO() {
		
	}

	public EstadoDTO(Estado obj) {
		super();
		id = obj.getId();
		nome = obj.getNome();
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
	

}
