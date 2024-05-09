package com.devsu.accountservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountTypeEnum {
    
    AHORROS("Ahorros"),
    CORRIENTE("Corriente"),
    ;
    
    private String description;
}
