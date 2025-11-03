package com.gestaohelio.common.exceptions;

public class ElementoNaoEncontradoException extends RuntimeException{


    public ElementoNaoEncontradoException() {
        super("Elemento não encontrado!");
    }
}
