package br.com.facol.config;

import br.com.facol.util.QueryParamsBuilder;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private String nomeController;
    private String nomeMetodo;
    private Map<String, Object> queryParams;

    public Request(String url){
        String[] partsUrl = url.replaceFirst("/", "")
                .split("[?]");

        String[] controleEMetodo = partsUrl[0].split("/");

        this.nomeController = Character.toUpperCase(controleEMetodo[0].charAt(0)) +
                controleEMetodo[0].substring(1) + "Controller";

        this.nomeMetodo = controleEMetodo[1];

        queryParams = partsUrl.length > 1
                ? new QueryParamsBuilder().comParametros(partsUrl[1]).build()
                : new HashMap<String, Object>();

    }

    public String getNomeController(){
        return this.nomeController;
    }

    public String getNomeMetodo(){
        return this.nomeMetodo;
    }

    public Map<String, Object> getQueryParams(){
        return this.queryParams;
    }
}
