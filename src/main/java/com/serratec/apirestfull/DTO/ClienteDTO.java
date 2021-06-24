package com.serratec.apirestfull.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.serratec.apirestfull.domain.Cliente;
import com.serratec.apirestfull.service.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty
	@Length(min=5, max=120,message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	


	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	private String senha;
	
	
	public ClienteDTO () {
		
	}
	
	public ClienteDTO (Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		senha = obj.getSenha();
	
	}


	public Integer getIdDTO() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNomeDTO() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmailDTO() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
