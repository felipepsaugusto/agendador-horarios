package com.felipepsaugusto.agendadorhorarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class AgendamentoExceptionHandler{

    @ExceptionHandler(AgendamentoException.class)
    public ResponseEntity<RestErrorMensage> agendamentoExceptionHandler(AgendamentoException exception){
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.badRequest().body(restErrorMensage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorMensage> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        return ResponseEntity.status(500).body(restErrorMensage);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RestErrorMensage> runtimeException(RuntimeException exception){
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.BAD_REQUEST ,exception.getMessage());
        return ResponseEntity.badRequest().body(restErrorMensage);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RestErrorMensage> methodArgumentoTypeMismatchExcpetion(MethodArgumentTypeMismatchException exception){
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.BAD_REQUEST ,exception.getMessage());
        return ResponseEntity.badRequest().body(restErrorMensage);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<RestErrorMensage> handleMissingParam(MissingServletRequestParameterException ex){
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.NO_CONTENT , "Parametro obrigatório ausente: " + ex.getParameterName());
        return ResponseEntity.badRequest().body(restErrorMensage);
    }

}
