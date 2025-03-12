package br.com.facol.service;

import java.util.List;

import br.com.facol.domain.Produto;

public interface ProdutoService {
	
	public Produto listarProduto();
	public List<Produto> listarProdutos();

}
