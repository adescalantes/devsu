package com.devsu.clientservice.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "movement", schema = "bank")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    @NotNull(message = "El tipo de cuenta no puede estar vacío")
    @JsonProperty("fecha")
    private Date startDate;
    @Column(name = "movement_type")
    @NotNull(message = "El tipo de cuenta no puede estar vacío")
    @JsonProperty("tipoCuenta")
    private String movementType;
    @Column(name = "amount")
    @NotNull(message = "El tipo de cuenta no puede estar vacío")
    @JsonProperty("valor")
    private BigDecimal amount;
    @Column(name = "balance")
    @NotNull(message = "El tipo de cuenta no puede estar vacío")
    @JsonProperty("saldo")
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonProperty("cuenta")
    @NotNull(message = "La cuenta no puede estar vacía")
    private Account account;
}
