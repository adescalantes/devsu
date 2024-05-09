package com.devsu.clientservice.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "account", schema = "bank")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Long accountNumber;
    @Column(name = "account_type")
    @NotNull(message = "El tipo de cuenta no puede estar vacío")
    @JsonProperty("tipoCuenta")
    private String accountType;
    @Column(name = "initial_balance")
    @PositiveOrZero(message = "El saldo debe ser mayor o igual a 0")
    @JsonProperty("saldoInicial")
    private BigDecimal initialBalance;
    @Column(name = "status")
    @JsonProperty("estado")
    private boolean status = true;
    @Column(name = "client_id")
    @NotNull(message = "El cliente id no puede estar vacío")
    @JsonProperty("clienteId")
    private Long clientId;
}
