package com.devsu.accountservice.service.implement;

import com.devsu.accountservice.api.ClientApi;
import com.devsu.accountservice.model.entity.Movement;
import com.devsu.accountservice.model.response.MovementResponse;
import com.devsu.accountservice.model.response.ReportResponse;
import com.devsu.accountservice.model.entity.Client;
import com.devsu.accountservice.service.IAccountService;
import com.devsu.accountservice.service.IMovementService;
import com.devsu.accountservice.service.IReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService implements IReportService {

    private final IMovementService movementService;
    private final ClientApi clientApi;
    
    @Override
    public ReportResponse findByCriteria(LocalDate startDate, LocalDate endDate, long clientId) {
            List<Movement> movement = movementService.findByCriteria(startDate, endDate, clientId);
            return generateReport(movement, clientId);
    }
    
    private ReportResponse generateReport(List<Movement> movements, long clientId) {
        Client client = clientApi.findClientById(clientId);
        List<MovementResponse> movementResponses = movements.stream()
                .map(movement -> MovementResponse.builder()
                        .accountNumber(movement.getAccount().getAccountNumber())
                        .statusAccount(movement.getAccount().isStatus())
                        .accountType(movement.getAccount().getAccountType().getDescription())
                        .amountMovement(movement.getAmount())
                        .movementType(movement.getMovementType().getDescription())
                        .balanceAvailable(movement.getAccount().getBalance())
                        .createDate(movement.getCreateDate())
                        .build()).toList();
        return ReportResponse.builder()
                .clientName(client.getName())
                .movements(movementResponses)
                .build();
    }
}
