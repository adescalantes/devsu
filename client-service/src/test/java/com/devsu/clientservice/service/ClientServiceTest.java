package com.devsu.clientservice.service;

import com.devsu.clientservice.exception.CustomException;
import com.devsu.clientservice.model.entity.Client;
import com.devsu.clientservice.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveClient_NewClient() {
        Client newClient = new Client();
        newClient.setIdentification("123456789");
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.empty());
        when(clientRepository.save(any(Client.class))).thenReturn(newClient);

        Client savedClient = clientService.save(newClient);
        assertEquals(newClient.getIdentification(), savedClient.getIdentification());
        verify(clientRepository).save(newClient);
    }

    @Test
    void testSaveClient_ExistingClient() {
        Client existingClient = new Client();
        existingClient.setIdentification("123456789");
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.of(existingClient));

        assertThrows(CustomException.class, () -> clientService.save(existingClient));
    }

    @Test
    void testFindById_ExistingClient() {
        Client client = new Client();
        client.setId(1L);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client found = clientService.findById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void testFindById_ClientNotFound() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> clientService.findById(1L));
    }

    @Test
    void testFindAll_ExistingClients() {
        List<Client> clients = List.of(new Client(), new Client());
        when(clientRepository.findAllByStatus(true)).thenReturn(clients);

        List<Client> results = clientService.findAll(true);
        assertFalse(results.isEmpty());
        assertEquals(2, results.size());
    }

    @Test
    void testFindAll_ClientsNotFound() {
        List<Client> clients = Collections.emptyList();
        when(clientRepository.findAllByStatus(true)).thenReturn(clients);

        assertThrows(CustomException.class, () -> clientService.findAll(true));
    }

    @Test
    void testUpdate_ExistingClient() {
        Client existingClient = new Client();
        existingClient.setId(1L);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(existingClient);

        Client updated = clientService.update(1L, new Client());
        assertNotNull(updated);
    }

    @Test
    void testDelete_ActiveClient() {
        Client activeClient = new Client();
        activeClient.setStatus(true);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(activeClient));

        clientService.delete(1L);
        verify(clientRepository).save(activeClient);
    }

    @Test
    void testDelete_InactiveClient() {
        Client inactiveClient = new Client();
        inactiveClient.setStatus(false);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(inactiveClient));
        assertThrows(CustomException.class, () -> clientService.delete(1L));
    }

}