package br.com.projeto.bibliotecaapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    @Getter private HttpStatus httpStatusCode;

    @Getter private String code;

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.httpStatusCode = HttpStatus.UNPROCESSABLE_ENTITY;
    }

    public BusinessException(HttpStatus httpStatusCode, String code, String message) {
        this(code, message);
        this.httpStatusCode = httpStatusCode;
    }
}
