package com.devsu.clientservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderEnum {
    
    M("masculino"),
    F("femenino"),
    OTRO("otro")
    ;
    
    private String description;
}
