package com.devsu.accountservice.api;

import com.devsu.accountservice.exception.ClientNotFoundException;
import com.devsu.accountservice.exception.CustomException;
import com.devsu.accountservice.exception.EnumError;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Configuration
public class ClientConfig {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    public static class CustomErrorDecoder implements ErrorDecoder {

        @Override
        public Exception decode(String methodKey, Response response) {
            String body = extractBody(response);
            if (response.status() == 400) {
                if (body.contains(EnumError.ERROR_CLIENT_NOT_FOUND_API.getMessage())) {
                    return new CustomException(EnumError.ERROR_CLIENT_NOT_FOUND_API.getMessage(), HttpStatus.BAD_REQUEST);
                }
                return new CustomException(EnumError.ERROR_API.getMessage() + " client " + body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new CustomException(EnumError.ERROR_API.getMessage() + " client " + body, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        private String extractBody(Response response) {
            try (Response.Body body = response.body()) {
                if (body != null) {
                    return new String(body.asInputStream().readAllBytes());
                }
            } catch (IOException e) {
                return "Error a leer response body";
            }
            return "No hay response body";
        }
    }
}