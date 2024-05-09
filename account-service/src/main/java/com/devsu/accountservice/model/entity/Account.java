package com.devsu.accountservice.model.entity;

import com.devsu.accountservice.model.enums.AccountTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
    @JsonProperty(value = "numeroCuenta")
    private Long accountNumber;
    @Column(name = "account_type")
    @NotNull(message = "El tipo de cuenta no puede estar vacío")
    @JsonProperty("tipoCuenta")
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum accountType;
    @Column(name = "balance")
    @PositiveOrZero(message = "El saldo debe ser mayor o igual a 0")
    @NotNull(message = "El saldo no puede esta vacío")
    @Digits(integer = 20, fraction = 2, message = "Solo se admiten como máximo 20 enteros y 2 decimales en saldoInicial")
    @JsonProperty("saldo")
    private BigDecimal balance;
    @Column(name = "status")
    @JsonProperty("estado")
    private boolean status = true;
    @ManyToOne
    @NotNull(message = "El clienteId no puede estar vacío")
    @JoinColumn(name = "client_id", nullable = false)
    @JsonProperty("cliente")
    private Client client;

    public Account(BigDecimal balance) {
        this.balance = balance;
    }
}
