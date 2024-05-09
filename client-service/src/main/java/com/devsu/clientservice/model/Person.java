package com.devsu.clientservice.model;

import com.devsu.clientservice.model.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person {

    @Column(name = "name")
    @NotEmpty(message = "El nombre no puede estar vacío")
    @JsonProperty("nombre")
    private String name;
    @Column(name = "gender")
    @NotNull(message = "El género no puede estar vacío")
    @JsonProperty("genero")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    @Column(name = "age")
    @PositiveOrZero(message = "La edad debe ser mayor o igual a 0")
    @NotNull(message = "El saldo no puede esta vacío")
    @JsonProperty("edad")
    private int age;
    @Column(name = "identification")
    @NotEmpty(message = "La identificacion no puede estar vacío")
    @JsonProperty("identificacion")
    private String identification;
    @Column(name = "address")
    @JsonProperty("direccion")
    private String address;
    @Column(name = "phone_number")
    @JsonProperty("telefono")
    private String phoneNumber;
    
    
}
