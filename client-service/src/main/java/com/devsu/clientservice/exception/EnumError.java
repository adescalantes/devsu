package com.devsu.clientservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumError {
    
    ERROR_CLIENT_IDENTIFICATION_EXISTS("Identificación de cliente ya existe"),
    ERROR_CLIENT_NOT_FOUND("Cliente no encontrado"),
    ERROR_CLIENTS_NOT_FOUND("No se encontraron clientes"),
    ERROR_CLIENT_STATUS_INACTIVE("El cliente no se encuentra activo"),
    ;
    
    private String message;
}
