package com.devsu.accountservice.controller;

import com.devsu.accountservice.model.response.ReportResponse;
import com.devsu.accountservice.service.IReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@Tag(name = "reportes")
public class ReportController {

    private final IReportService reportService;
    
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Metodo para obtener reportes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ReportResponse findByCriteria(
            @RequestParam(name = "fechaInicio") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaInicio,
            @RequestParam(name = "fechaFin") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaFin,
            @RequestParam(name = "clienteId") long clientId
            ) {
        return reportService.findByCriteria(fechaInicio, fechaFin, clientId);
    }
}
