package com.atorres.nttdata.accountmsf.controller;

import com.atorres.nttdata.accountmsf.exception.CustomException;
import com.atorres.nttdata.accountmsf.exception.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientRequestException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorDto> customExceptionHandler(CustomException ex){
        ErrorDto error = ErrorDto
                .builder()
                .httpStatus(ex.getStatus())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error,ex.getStatus());
    }

    /**
     * Metodo por si falla al conectarse el client Feign
     * @param ex excepcion
     * @return errordto
     */
    @ExceptionHandler(value = WebClientRequestException.class)
    public ResponseEntity<ErrorDto> webClientResponseHandler(WebClientRequestException ex){
        ErrorDto error = ErrorDto
            .builder()
            .httpStatus(HttpStatus.BAD_REQUEST)
            .message("Error en la solicitud: " + ex.getMessage())
            .build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

}
