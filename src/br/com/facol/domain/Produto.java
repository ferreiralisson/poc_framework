package br.com.facol.domain;

import br.com.facol.util.NomeTagXml;

@NomeTagXml("product")
public class Produto {

    private Integer id;
    @NomeTagXml("name")
    private String nome;
    @NomeTagXml("brand")
    private String marca;

    public Produto(Integer id, String nome, String marca) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
    }

    public Integer getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public String getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }
}
