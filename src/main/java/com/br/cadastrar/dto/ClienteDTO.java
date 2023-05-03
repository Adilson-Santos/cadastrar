package com.br.cadastrar.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.br.cadastrar.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ter entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ter entre 5 e 120 caracteres")
	private String endereco;
	
	private Integer numero;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ter entre 5 e 120 caracteres")
	private String bairro;
	
	private Integer cep;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 40, message = "O tamanho deve ter entre 5 e 40 caracteres")
	private String cidade;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 40, message = "O tamanho deve ter entre 5 e 40 caracteres")
	private String estado;

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
	    nome = obj.getNome();
	    endereco = obj.getEndereco();
		numero = obj.getNumero();
		bairro = obj.getBairro();
		cep = obj.getCep();
		cidade = obj.getCidade();
		estado = obj.getEstado();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}
