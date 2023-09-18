package br.com.projeto.bibliotecaapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Error {
    NOT_AVAILABLE("Livro não está disponível para empréstimo.", 1000),
    LIMIT_REACHED("Usuário atingiu o limite de livros emprestados.", 1001),
    DUPLICATE_DATA("Usuário já possui um exemplar desse livro emprestado."),
    LATE_DELIVERY("Usuário possui um livro com entrega atrasada.");

    private final String message;
    private final int code;
    private final HttpStatus status;

    Error(String message, int code) {
        this.message = message;
        this.code = code;
        this.status = HttpStatus.UNPROCESSABLE_ENTITY;
    }

    Error(String message) {
        this.message = message;
        this.code = 0;
        this.status = HttpStatus.UNPROCESSABLE_ENTITY;
    }

    public BusinessException toBusinessException() {
        return new BusinessException(String.format("%04d", code), message);
    }

    public BusinessException toBusinessException(Integer code) {
        return new BusinessException(status, String.format("%04d", code), message);
    }
}
