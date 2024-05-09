package com.devsu.clientservice.service;

import com.devsu.clientservice.model.entity.Client;

import java.util.List;

public interface IClientService {
    Client save(Client client);

    Client findById(long id);

    List<Client> findAll(Boolean statusEnum);

    Client update(long id, Client client);

    void delete(long id);
}
