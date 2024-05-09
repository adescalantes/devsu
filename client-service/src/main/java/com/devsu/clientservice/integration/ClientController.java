package com.devsu.clientservice.integration;

import com.devsu.clientservice.model.entity.Client;
import com.devsu.clientservice.service.IClientService;
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
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "clientes")
public class ClientController {
    
    private final IClientService clientService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Metodo para guardar cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Client save(@RequestBody @Valid Client client) {
        return clientService.save(client);
    }
    
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Metodo para obtener todos los clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<Client> findAll(@RequestParam(name = "estado", required = false) Boolean status) {
        return clientService.findAll(status);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Metodo para obtener un cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Client findById(@PathVariable long id) {
        return clientService.findById(id);
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Metodo para actualizar cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Client update(@PathVariable long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Metodo para eliminar un cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void delete(@PathVariable long id) {
        clientService.delete(id);
    }
}
