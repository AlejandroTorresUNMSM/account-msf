package com.atorres.nttdata.accountmsf.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorDto {
    private HttpStatus httpStatus;
    private String message;
}
