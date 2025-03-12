package br.com.facol.config.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ManipuladorConstrutor {

    private Constructor<?> constructor;
    public ManipuladorConstrutor(Constructor<?> construtor) {
        this.constructor = construtor;
    }

    public Object invoca() {
        try {
            return constructor.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro no construtor!", e.getTargetException());
        }
    }
}
