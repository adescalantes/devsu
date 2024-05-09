package com.devsu.accountservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportResponse {

    @JsonProperty("cliente")
    private String clientName;
    @JsonProperty("movimientos")
    private List<MovementResponse> movements;
    
}
