package br.com.facol.controller;

import java.util.List;
import java.util.stream.Collectors;

import br.com.facol.domain.Produto;
import br.com.facol.service.ProdutoService;

public class ProdutoController {

	private ProdutoService service;

	public ProdutoController(ProdutoService service) {
		this.service = service;
	}

	public Produto listarProduto() {
		return service.listarProduto();
	}

	private List<Produto> listarProdutos(String nome, String marca) {
		return service.listarProdutos().stream()
				.filter(p -> p.getNome().equals(nome.toUpperCase()) && p.getMarca().equals(marca.toUpperCase()))
				.collect(Collectors.toUnmodifiableList());
	}

	private List<Produto> listarProdutos() {
		return service.listarProdutos();
	}

}
