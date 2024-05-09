package com.devsu.clientservice.service;

import com.devsu.clientservice.exception.CustomException;
import com.devsu.clientservice.exception.EnumError;
import com.devsu.clientservice.model.entity.Client;
import com.devsu.clientservice.repository.ClientRepository;
import com.devsu.clientservice.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        log.info("Inicio metodo save client {}", client);
        clientRepository.findByIdentification(client.getIdentification())
                .ifPresent(clientExist -> {
                    throw new CustomException(EnumError.ERROR_CLIENT_IDENTIFICATION_EXISTS.getMessage(), HttpStatus.BAD_REQUEST);
                });
        return clientRepository.save(client);
    }

    @Override
    public Client findById(long id) {
        log.info("Inicio metodo findById {}", id);
        return clientRepository.findById(id)
                .orElseThrow(() -> new CustomException(EnumError.ERROR_CLIENT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @Override
    public List<Client> findAll(Boolean status) {
        log.info("Inicio metodo findAll {}", status);

        return Optional.ofNullable(status)
                .map(statusClient -> {
                    List<Client> clients = clientRepository.findAllByStatus(statusClient);
                    if (clients.isEmpty()) {
                        throw new CustomException(EnumError.ERROR_CLIENTS_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
                    }
                    return clients;
                })
                .orElseGet(() -> {
                    List<Client> clients = clientRepository.findAll();
                    if (clients.isEmpty()) {
                        throw new CustomException(EnumError.ERROR_CLIENTS_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
                    }
                    return clients;
                });
    }

    @Override
    public Client update(long id, Client client) {
        log.info("Inicio metodo update id {} - client {}", id, client);
        return clientRepository.findById(id)
                .map(clientExist -> {
                    Util.copyNonNullProperties(clientExist, client);
                    return clientRepository.save(clientExist);
                })
                .orElseThrow(() -> new CustomException(EnumError.ERROR_CLIENT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @Override
    public void delete(long id) {
        log.info("Inicio metodo delete id {}", id);
        clientRepository.findById(id)
                .ifPresentOrElse(
                        client -> {
                            if (!client.isStatus()) {
                                throw new CustomException(EnumError.ERROR_CLIENT_STATUS_INACTIVE.getMessage(), HttpStatus.BAD_REQUEST);
                            }
                            client.setStatus(false);
                            clientRepository.save(client);
                        },
                        () -> {
                            throw new CustomException(EnumError.ERROR_CLIENT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
                        });
    }
}
