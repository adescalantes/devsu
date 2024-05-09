package com.devsu.accountservice.api;

import com.devsu.accountservice.model.entity.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-api", url = "${client-api.url}")
public interface ClientApi {
    @GetMapping("/api/clientes/{id}")
    Client findClientById(@PathVariable long id);

}
