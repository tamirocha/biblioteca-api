package br.com.projeto.bibliotecaapi.config;


import br.com.projeto.bibliotecaapi.exception.BusinessException;
import br.com.projeto.bibliotecaapi.exception.ErrorResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestControllerExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ErrorResponse> handle(BusinessException ex) {
        HttpStatus status = ex.getHttpStatusCode();
        return RestControllerAdviceUtils.handleBusinessException(status, ex);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<ErrorResponse> handle(HttpRequestMethodNotSupportedException ex) {
        return RestControllerAdviceUtils.handleThrowable(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public final ResponseEntity<ErrorResponse> handle(HttpMediaTypeNotSupportedException ex) {
        return RestControllerAdviceUtils.handleThrowable(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public final ResponseEntity<ErrorResponse> handle(HttpMediaTypeNotAcceptableException ex) {
        return RestControllerAdviceUtils.handleThrowable(HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public final ResponseEntity<ErrorResponse> handle(MissingServletRequestParameterException ex) {
        return RestControllerAdviceUtils.handleThrowable(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public final ResponseEntity<ErrorResponse> handle(ServletRequestBindingException ex) {
        return RestControllerAdviceUtils.handleThrowable(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public final ResponseEntity<ErrorResponse> handle(TypeMismatchException ex) {
        return RestControllerAdviceUtils.handleThrowable(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorResponse> handle(HttpMessageNotReadableException ex) {
        return RestControllerAdviceUtils.handleThrowable(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex) {
        return RestControllerAdviceUtils.handleMethodArgumentNotValidException(
                HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public final ResponseEntity<ErrorResponse> handle(MissingServletRequestPartException ex) {
        return RestControllerAdviceUtils.handleThrowable(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public final ResponseEntity<ErrorResponse> handle(BindException ex) {
        return RestControllerAdviceUtils.handleThrowable(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public final ResponseEntity<ErrorResponse> handle(NoHandlerFoundException ex) {
        return RestControllerAdviceUtils.handleThrowable(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public final ResponseEntity<ErrorResponse> handle(AsyncRequestTimeoutException ex) {
        return RestControllerAdviceUtils.handleThrowable(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<ErrorResponse> handle(ResponseStatusException ex) {
        return RestControllerAdviceUtils.handleThrowable(ex.getStatus());
    }
}
