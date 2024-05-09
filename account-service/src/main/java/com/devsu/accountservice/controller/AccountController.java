package com.devsu.accountservice.controller;

import com.devsu.accountservice.model.entity.Account;
import com.devsu.accountservice.service.IAccountService;
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
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
@Tag(name = "cuentas")
public class AccountController {
    
    private final IAccountService accountService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Metodo para guardar cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Account save(@RequestBody @Valid Account account) {
        return accountService.save(account);
    }
    
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Metodo para obtener todos las cuentas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<Account> findAll(@RequestParam(name = "estado", required = false) Boolean status) {
        return accountService.findAll(status);
    }
    @GetMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Metodo para obtener una cuenta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Account findById(@PathVariable long accountNumber) {
        return accountService.findByAccountNumber(accountNumber);
    }


    @PatchMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Metodo para actualizar cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Account update(@PathVariable long accountNumber, @RequestBody Account account) {
        return accountService.update(accountNumber, account);
    }

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Metodo para eliminar un cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void delete(@PathVariable long accountNumber) {
        accountService.delete(accountNumber);
    }
}
