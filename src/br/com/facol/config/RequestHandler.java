package br.com.facol.config;

import java.util.Map;

import br.com.facol.config.ioc.ContainerIoC;
import br.com.facol.config.reflexao.ManipuladorObjeto;
import br.com.facol.config.reflexao.Reflexao;
import br.com.facol.util.ConversorXML;

public class RequestHandler {

	private String pacoteBasico;
	private ContainerIoC ioc;

	public RequestHandler(String pacoteBasico) {
		this.pacoteBasico = pacoteBasico;
		this.ioc = new ContainerIoC();
	}

	public Object executa(String url) {
		// /controller/method
		Request request = new Request(url);
		String nomeController = request.getNomeController();
		String nomeMetodo = request.getNomeMetodo();
		Map<String, Object> params = request.getQueryParams();

		Class<?> classeControle = new Reflexao().getClasse(pacoteBasico + nomeController);
		Object instanciaControle = ioc.getInstancia(classeControle);

		Object retorno = new ManipuladorObjeto(instanciaControle)
				.getMetodo(nomeMetodo, params)
				.comTratamentoDeExcecao((metodo, ex) -> {
					System.out.println("Erro no método " + metodo.getName() + " da classe "
							+ metodo.getDeclaringClass().getName() + ".\n\n");
					throw new RuntimeException("ERRO!");
				})
				.invoca();


		retorno = new ConversorXML().converte(retorno);

		return retorno;
	}
	
	public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {
		ioc.registra(tipoFonte, tipoDestino);
	}
}
