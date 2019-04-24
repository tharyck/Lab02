package com.produto.produto.service;

import com.produto.produto.model.Produto;
import com.produto.produto.repository.ProdutoRepository;
import com.produto.utils.exceptions.NotFoundRuntimeException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

	private ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public Produto show(Long id){
		try {
			return produtoRepository.findById(id).orElseThrow(NotFoundRuntimeException::new);
		} catch (IllegalArgumentException iae) {
			throw new NotFoundRuntimeException();
		}
	}

	public List<Produto> index(){
		return produtoRepository.findAll();
	}

	public Produto create(Produto produto){
		try{
			return produtoRepository.save(produto);
		}catch (Exception e){
			throw new RuntimeException("Erro ao criar Recurso" +  e);
		}
	}

	public Produto update(Long id, Produto produto){
		if (!produtoRepository.existsById(id)) {
			throw new NotFoundRuntimeException();
		}
		try {
			produto.setId(id);
			return produtoRepository.save(produto);
		}catch (Exception e){
			throw new RuntimeException("Erro ao atualizar Recurso" +  e);
		}
	}

	public void delete(Long id){
		if (!produtoRepository.existsById(id)) {
			throw new NotFoundRuntimeException();
		}
		try {
			produtoRepository.deleteById(id);
		}catch (Exception e){
			throw new RuntimeException("Erro ao excluir Recurso" +  e);
		}
	}


}