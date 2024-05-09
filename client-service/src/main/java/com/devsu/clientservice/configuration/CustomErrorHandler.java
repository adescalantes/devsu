package com.devsu.clientservice.configuration;

import com.devsu.clientservice.exception.CustomException;
import com.devsu.clientservice.model.ApiError;
import com.devsu.clientservice.model.CustomErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class CustomErrorHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("Error handleMethodArgumentNotValidException: {}", exception.getMessage());
        List<ApiError> apiErrorList = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ApiError(fieldError.getDefaultMessage()))
                .toList();
        return new CustomErrorResponse(apiErrorList);
    }

    @ExceptionHandler({WebExchangeBindException.class})
            @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorResponse handleWebExchangeBindException(WebExchangeBindException exception) {
        log.error("Error WebExchangeBindException: {}", exception.getMessage());
        List<ApiError> apiErrorList = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ApiError(fieldError.getDefaultMessage()))
                .toList();
        return new CustomErrorResponse(apiErrorList);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<CustomErrorResponse> handleCustomException(CustomException exception) {
        log.error("Error handleCustomException: {}", exception.getMessage());
        List<ApiError> apiErrorList = List.of(new ApiError(exception.getMessage()));
        return ResponseEntity.status(exception.getHttpStatus())
                .body(new CustomErrorResponse(apiErrorList));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomErrorResponse handleException(Exception exception) {
        log.error("Error handleException", exception);
        List<ApiError> apiErrorList = List.of(new ApiError(Objects.requireNonNull(exception.getMessage())));
        return new CustomErrorResponse(apiErrorList);
    }
}
