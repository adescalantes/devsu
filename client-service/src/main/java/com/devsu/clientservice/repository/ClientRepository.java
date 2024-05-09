package com.devsu.clientservice.repository;

import com.devsu.clientservice.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByIdentification(String identification);

    List<Client> findAllByStatus(boolean status);
}
