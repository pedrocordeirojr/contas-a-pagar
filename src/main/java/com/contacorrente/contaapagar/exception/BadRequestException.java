package com.contacorrente.contaapagar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {
    private static final long serialVersionUID = 1L;

    public BadRequestException(String defaultMessage, String internalMessage, String code) {
        super(defaultMessage, internalMessage, code);
    }

    public BadRequestException(String defaultMessage, String code) {
        super(defaultMessage, code);
    }
}