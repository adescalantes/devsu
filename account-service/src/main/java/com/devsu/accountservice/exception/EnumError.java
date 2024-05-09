package com.devsu.accountservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumError {
    
    ERROR_ACCOUNT_NOT_FOUND("Cuenta no encontrada"),
    ERROR_ACCOUNTS_NOT_FOUND("No se encontraron cuentas"),
    ERROR_ACCOUNT_STATUS_INACTIVE("La cuenta se encuentra desactivada"),
    ERROR_API("Error al llamar al servicio"),
    ERROR_CLIENT_NOT_FOUND_API("Cliente no encontrado"),
    ERROR_MOVEMENT_TYPE_NOT_FOUND("No existe tipo de movimiento"),
    ERROR_INSUFFICIENT_BALANCE("Saldo no disponible"),
    ERROR_CLIENT_STATUS_INACTIVE("El cliente no se encuentra activo"),
    ERROR_MOVEMENT_NOT_FOUND("Movimiento no encontrado"),
    ERROR_MOVEMENTS_NOT_FOUND("No se encontraron movimientos"),
    ;
    
    private String message;
    
}
