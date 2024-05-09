package com.devsu.accountservice.model.entity;

import com.devsu.accountservice.model.enums.MovementTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Column(name = "create_date")
    @JsonProperty(value = "fecha", access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createDate;
    @Column(name = "movement_type")
    @NotNull(message = "El tipo de movimiento no puede estar vacío")
    @JsonProperty("tipoMovimiento")
    @Enumerated(EnumType.STRING)
    private MovementTypeEnum movementType;
    @Column(name = "amount")
    @PositiveOrZero(message = "El valor debe ser mayor o igual a 0")
    @Digits(integer = 20, fraction = 2, message = "Solo se admiten como máximo 20 enteros y 2 decimales en valor")
    @JsonProperty("valor")
    private BigDecimal amount;
    @Column(name = "initial_balance")
    @JsonProperty(value = "saldoInicial", access = JsonProperty.Access.READ_ONLY)
    private BigDecimal initialBalance;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @NotNull(message = "La cuenta no puede estar vacía")
    @JsonProperty("cuenta")
    private Account account;
}
