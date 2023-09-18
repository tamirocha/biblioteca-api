package br.com.projeto.bibliotecaapi.config;


import br.com.projeto.bibliotecaapi.exception.BusinessException;
import br.com.projeto.bibliotecaapi.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RestControllerAdviceUtils {
    public static ResponseEntity<ErrorResponse> handleThrowable(HttpStatus status) {
        ErrorResponse response =
                ErrorResponse.builder()
                        .statusCode(status.value())
                        .statusMessage(status.getReasonPhrase())
                        .errors(List.of())
                        .build();

        return ResponseEntity.status(status.value()).body(response);
    }

    public static ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            HttpStatus status, MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach(
                        (error) -> {
                            String fieldName = ((FieldError) error).getField();
                            String errorMessage = error.getDefaultMessage();
                            errors.put(fieldName, errorMessage);
                        });

        ErrorResponse response =
                ErrorResponse.builder()
                        .statusCode(status.value())
                        .statusMessage(status.getReasonPhrase())
                        .errors(List.of(errors))
                        .build();

        return ResponseEntity.status(status.value()).body(response);
    }

    public static ResponseEntity<ErrorResponse> handleBusinessException(
            HttpStatus status, BusinessException ex) {

        HashMap<String, Object> error = new HashMap<>();
        error.put("code", ex.getCode());
        error.put("message", ex.getMessage());

        ErrorResponse response =
                ErrorResponse.builder()
                        .statusCode(status.value())
                        .statusMessage(status.getReasonPhrase())
                        .errors(List.of(error))
                        .build();

        return ResponseEntity.status(status.value()).body(response);
    }
}
