package com.devsu.accountservice.service;

import com.devsu.accountservice.model.entity.Account;
import com.devsu.accountservice.model.entity.Movement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IMovementService {
    Movement save(Movement movement);

    Movement findById(long id);

    List<Movement> findAll();

    void delete(long id);
    
    List<Movement> findByCriteria(LocalDate startDate, LocalDate endDate, long clientId);
}
