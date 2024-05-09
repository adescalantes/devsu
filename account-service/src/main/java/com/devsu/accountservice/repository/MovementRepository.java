package com.devsu.accountservice.repository;

import com.devsu.accountservice.model.entity.Movement;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query("SELECT m FROM Movement m WHERE m.createDate BETWEEN :startDate AND :endDate " +
            "AND m.account.client.id = :clientId ORDER BY m.createDate DESC")
    List<Movement> findByDateRangeAndClientId(@Param("startDate") LocalDateTime startDate,
                                              @Param("endDate") LocalDateTime endDate,
                                              @Param("clientId") Long clientId);
}
