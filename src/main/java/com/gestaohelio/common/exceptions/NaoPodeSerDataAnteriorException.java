package com.gestaohelio.common.exceptions;

public class NaoPodeSerDataAnteriorException extends RuntimeException{

    public NaoPodeSerDataAnteriorException() {
        super("A data de saída não pode ser anterior à data de entrada.");
    }
}
