package com.produto.produto.dto;

import io.swagger.annotations.ApiModelProperty;

public class ProdutoInput {

	@ApiModelProperty(example = "produto 1")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
