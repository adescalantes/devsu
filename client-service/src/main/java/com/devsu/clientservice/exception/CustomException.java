package com.devsu.clientservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
public class CustomException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2304286352276405045L;

    private final String message;
    
    private final HttpStatus httpStatus;

}
