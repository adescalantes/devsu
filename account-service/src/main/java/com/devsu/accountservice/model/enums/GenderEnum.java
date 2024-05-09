package com.devsu.accountservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderEnum {
    
    M("Masculino"),
    F("Femenino"),
    OTRO("Otro")
    ;
    
    private String description;
}
