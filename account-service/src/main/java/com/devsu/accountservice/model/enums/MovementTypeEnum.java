package com.devsu.accountservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MovementTypeEnum {
    
    RETIRO("Retiro"),
    DEPOSITO("Deposito"),
    ;
    
    private String description;
}
