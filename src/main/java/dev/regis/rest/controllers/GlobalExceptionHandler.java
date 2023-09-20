package dev.regis.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String errorMsg = "Erro na desserialização do Objeto JSON! \n" + ex.getMessage();
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String errorMsg = "Erro no tipo de argumento! \n" + ex.getMessage();
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<String> handleMissingPathVariable(MissingPathVariableException ex) {
        String errorMsg = "Erro em variável esperada na url! \n" + ex.getMessage();
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }
}
