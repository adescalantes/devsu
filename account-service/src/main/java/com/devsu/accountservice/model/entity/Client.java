package com.devsu.accountservice.model.entity;

import com.devsu.accountservice.model.Person;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "client", schema = "bank")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, exclude = "password")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id")
    private Long id;
    @Column(name = "password")
    @NotEmpty(message = "La contraseña no puede estar vacío")
    @JsonProperty(value = "contrasenia", access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(name = "status")
    @JsonProperty("estado")
    private boolean status = true;
}
