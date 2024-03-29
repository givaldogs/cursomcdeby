package com.ggs.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.ggs.cursomc.domain.Categoria;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CategoriaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	//@NotEmpty(message="Preenchimento Obrigatorio")
	//@NotNull(message="Preenchimento Obrigatorio")
	@NotEmpty(message="Preenchimento Obrigatorio")
    @Length(min =5, max=80, message="O tamanho deve ser entre 5 a 80 caracteres")
	private String nome;

	public CategoriaDto() {

	}

	public CategoriaDto(Categoria obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();

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
