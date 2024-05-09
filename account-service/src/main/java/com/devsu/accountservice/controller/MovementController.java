package com.devsu.accountservice.controller;

import com.devsu.accountservice.model.entity.Account;
import com.devsu.accountservice.model.entity.Movement;
import com.devsu.accountservice.service.IMovementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
@Tag(name = "movimientos")
public class MovementController {
    
    private final IMovementService movementService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Metodo para guardar un movimiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Movement save(@RequestBody @Valid Movement movement) {
        return movementService.save(movement);
    }
    
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Metodo para obtener todos los movimientos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<Movement> findAll() {
        return movementService.findAll();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Metodo para obtener un movimiento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Movement findById(@PathVariable long id) {
        return movementService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Metodo para eliminar un movimiento por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void delete(@PathVariable long id) {
        movementService.delete(id);
    }
}
