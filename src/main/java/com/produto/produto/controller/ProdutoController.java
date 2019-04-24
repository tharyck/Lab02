package com.produto.produto.controller;

import com.produto.produto.dto.ProdutoIO;
import com.produto.produto.dto.ProdutoInput;
import com.produto.produto.dto.ProdutoOutput;
import com.produto.produto.model.Produto;
import com.produto.produto.service.ProdutoService;
import com.produto.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(RestConstants.PRODUTO_URI)
@Api(tags = "produto")
@CrossOrigin

public class ProdutoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class.getSimpleName());

	private ProdutoIO produtoIO;

	private ProdutoService produtoService;

	@Autowired
	public ProdutoController(ProdutoIO produtoIO, ProdutoService produtoService) {
		this.produtoIO = produtoIO;
		this.produtoService = produtoService;
	}

	@PostMapping({"/", ""})
	@ApiOperation(value = "Criar um produto")
	public ResponseEntity<?> create(@Valid @RequestBody ProdutoInput produtoInput) {
		LOGGER.info("trying create new students");
		Produto produto = produtoIO.mapTo(produtoInput);
		Produto savedProduto = produtoService.create(produto);
		//@formatter:off
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedProduto.getId())
				.toUri();
		LOGGER.info("Produto " + produto.getId() + " create at " + location);
		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Retorne todos os Produtos")
	@GetMapping({"/", ""})
	// @formatter:off
	public List<ProdutoOutput> index() {
		LOGGER.info("index students");
		return produtoIO.toList(produtoService.index());
	}
	// @formatter:on

	@ApiOperation(value = "Retorne um Produto")
	@GetMapping({"/{id}/", "/{id}"})
	// @formatter:off
	public ProdutoOutput show(
			@PathVariable("id") Long id) {
		LOGGER.info("show produto " + id);
		return produtoIO.mapTo(produtoService.show(id));
	}

	@PutMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Atualize um Produto")
	public ResponseEntity<?> update(
			//@formatter:off
			@Min(value= 1) @PathVariable("id") Long id,
			@Valid @RequestBody ProdutoInput produtoInput) {
		Produto produto = produtoIO.mapTo(produtoInput);
		LOGGER.info("trying update produto " + produto.getNome());
		produtoService.update(id, produto);
		LOGGER.info("produto " + produto.getNome() + " updated");
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Deletar um Produto")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		LOGGER.info("trying deleting produto " + id);
		produtoService.delete(id);
		LOGGER.info("produto " + id + " deleted");
		return ResponseEntity.ok().build();
	}
}
