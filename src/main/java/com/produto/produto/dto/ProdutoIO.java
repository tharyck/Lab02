package com.produto.produto.dto;

import com.produto.produto.model.Produto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component("produtoIO")
public class ProdutoIO {

	private ModelMapper modelMapper;

	final Converter<ProdutoInput, Produto> produtoInputConverter = new Converter<ProdutoInput, Produto>() {
		@Override
		public Produto convert(MappingContext<ProdutoInput, Produto> context) {
			ProdutoInput input = context.getSource();
			Produto produto =  new Produto();
			produto.setNome(input.getNome());

			return produto;
		}
	};

	final Converter<Produto, ProdutoOutput> produtoOutputConverter = new Converter<Produto, ProdutoOutput>() {
		@Override
		public ProdutoOutput convert(MappingContext<Produto, ProdutoOutput> context) {
			Produto input = context.getSource();
			ProdutoOutput produtoOutput = new ProdutoOutput();
			produtoOutput.setId(input.getId());
			produtoOutput.setNome(input.getNome());

			return produtoOutput;
		}
	};

	public ProdutoIO() {
		this.modelMapper = new ModelMapper();
		this.modelMapper.addConverter(produtoInputConverter);
		this.modelMapper.addConverter(produtoOutputConverter);
	}

	public Produto mapTo(ProdutoInput produtoInput) {
		return this.modelMapper.map(produtoInput, Produto.class);
	}

	public ProdutoOutput mapTo(Produto produto) {
		return this.modelMapper.map(produto, ProdutoOutput.class);
	}

	public List<ProdutoOutput> toList(List<Produto> source) {
		Type type = new TypeToken<List<ProdutoOutput>>() {}.getType();
		return this.modelMapper.map(source, type);
	}

}
