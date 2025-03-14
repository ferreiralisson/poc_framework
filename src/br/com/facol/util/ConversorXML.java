package br.com.facol.util;

import java.lang.reflect.Field;
import java.util.Collection;

public class ConversorXML {
	
	public String converte(Object objeto) {
		//<lista>
		//<produto>
//			<nome>Produto 1</nome>
//			<valor>20.0</valor>
//			<marca>Marca 1</marca>
		//</produto>
		//<produto>
//			<nome>Produto 2</nome>
//			<valor>20.0</valor>
//			<marca>Marca 2</marca>
		//</produto>
		//</lista>
		try {
			Class<?> classeObjeto = objeto.getClass();
			StringBuilder xmlBuilder = new StringBuilder();
			
			if(objeto instanceof Collection) {
				Collection<?> colecao = (Collection<?>) objeto; 
				
				xmlBuilder.append("<lista>");
				
				for(Object obj : colecao) {
					String xml = converte(obj);
					xmlBuilder.append(xml);
				}
				
				xmlBuilder.append("</lista>");
			} else {
				
				NomeTagXml anotacaoClasse = classeObjeto.getDeclaredAnnotation(NomeTagXml.class);
				
				String nomeClasse = 
						anotacaoClasse == null
						? classeObjeto.getName()
						: anotacaoClasse.value();
				
				xmlBuilder.append("<"+nomeClasse+">");
				
				for(Field atributo : classeObjeto.getDeclaredFields()) {
					atributo.setAccessible(true);
					
					NomeTagXml anotacaoAtributo = atributo.getDeclaredAnnotation(NomeTagXml.class);
					
					String nomeAtributo = 
							anotacaoAtributo == null
							? atributo.getName()
							: anotacaoAtributo.value();
					
					Object valorAtributo = atributo.get(objeto);
					
					xmlBuilder.append("<"+nomeAtributo+">");
					xmlBuilder.append(valorAtributo);
					xmlBuilder.append("</"+nomeAtributo+">");
				}
				
				xmlBuilder.append("</"+nomeClasse+">");
			}
			
			return xmlBuilder.toString();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}

}
