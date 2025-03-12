package br.com.facol.util;

import java.lang.reflect.Field;
import java.util.Collection;

public class ConversorXML {
	
	public String converte(Object objeto) {
		Class<?> classeObjeto = objeto.getClass();
		StringBuilder xmlBuilder = new StringBuilder();
		
		try {
			if(objeto instanceof Collection) {
				Collection<?> colecao = (Collection<?>) objeto; 
				
				xmlBuilder.append("<lista>");
				
				for(Object obj : colecao) {
					String xml = converte(obj);
					xmlBuilder.append(xml);
				}
				
				xmlBuilder.append("</lista>");
			} else {
				
				NomeTagXml nomeTagXml = classeObjeto.getDeclaredAnnotation(NomeTagXml.class);
				
				String nomeClasse = 
						nomeTagXml == null
						? classeObjeto.getName()
						: nomeTagXml.value();
				
				xmlBuilder.append("<"+nomeClasse+">");
				
				for(Field atributo : classeObjeto.getDeclaredFields()) {
					atributo.setAccessible(true);
					
					NomeTagXml nomeTag = classeObjeto.getDeclaredAnnotation(NomeTagXml.class);
					
					String nomeAtributo = nomeTag == null
							? atributo.getName()
									: nomeTag.value();
					
					Object valorAtributo = atributo.get(objeto);
					
					xmlBuilder.append("<"+nomeAtributo+">");
					xmlBuilder.append(valorAtributo);
					xmlBuilder.append("</"+nomeAtributo+">");
				}
				
				xmlBuilder.append("</"+nomeClasse+">");
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return xmlBuilder.toString();
	}

}
