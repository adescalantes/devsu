package com.devsu.accountservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementResponse {
    
    @JsonProperty("fecha")
    private LocalDateTime createDate;
    @JsonProperty("numeroCuenta")
    private long accountNumber;
    @JsonProperty("tipoCuenta")
    private String accountType;
    @JsonProperty("estadoCuenta")
    private boolean statusAccount;
    @JsonProperty("valorMovimiento")
    private BigDecimal amountMovement;
    @JsonProperty("tipoMovimiento")
    private String movementType;
    @JsonProperty("saldoDisponible")
    private BigDecimal balanceAvailable;
    
}
