package br.com.facol.service;

import java.util.List;

import br.com.facol.domain.Produto;
import br.com.facol.repository.ProdutoRepository;

public class ProdutoServiceImpl implements ProdutoService {

	private ProdutoRepository repository;

	public ProdutoServiceImpl(ProdutoRepository repository) {
		this.repository = repository;
	}

	@Override
	public Produto listarProduto() {
		return repository.listarProduto();
	}

	@Override
	public List<Produto> listarProdutos() {
		return repository.listarProdutos();
	}

}
