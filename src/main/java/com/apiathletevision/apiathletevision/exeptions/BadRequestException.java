package com.apiathletevision.apiathletevision.exeptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}

