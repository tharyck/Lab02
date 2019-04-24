package com.produto.produto.dto;

import io.swagger.annotations.ApiModelProperty;

public class ProdutoOutput {


	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "produto 1")
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
