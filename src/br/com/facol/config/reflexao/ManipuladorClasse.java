package br.com.facol.config.reflexao;

import java.lang.reflect.Constructor;

public class ManipuladorClasse {

    private Class<?> classe;
    public ManipuladorClasse(Class<?> classe) {
        this.classe = classe;
    }

    public ManipuladorConstrutor getConstrutorPadrao() {
        try {
            Constructor<?> construtorPadrao = classe.getDeclaredConstructor();
            return new ManipuladorConstrutor(construtorPadrao);
        } catch (SecurityException | NoSuchMethodException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ManipuladorObjeto criarInstancia() {
        Object instancia = getConstrutorPadrao().invoca();
        return new ManipuladorObjeto(instancia);
    }
}
