package com.apiathletevision.apiathletevision.exeptions;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("Não autorizado");
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
