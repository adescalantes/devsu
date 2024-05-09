package com.devsu.accountservice.service.implement;

import com.devsu.accountservice.exception.CustomException;
import com.devsu.accountservice.exception.EnumError;
import com.devsu.accountservice.model.entity.Account;
import com.devsu.accountservice.model.entity.Movement;
import com.devsu.accountservice.model.enums.MovementTypeEnum;
import com.devsu.accountservice.repository.MovementRepository;
import com.devsu.accountservice.service.IAccountService;
import com.devsu.accountservice.service.IMovementService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovementService implements IMovementService {

    private final MovementRepository movementRepository;
    private final IAccountService accountService;

    @Override
    @Transactional
    public Movement save(Movement movement) {
        log.info("Inicio metodo save {}", movement);
        Account account = accountService.findValidAccount(movement.getAccount().getAccountNumber());
        movement.setAccount(account);
        movement.setCreateDate(LocalDateTime.now(ZoneId.of("GMT-5")));
        BigDecimal actualBalance = calculateBalance(account.getBalance(), movement.getAmount(), movement.getMovementType(), false);
        movement.setInitialBalance(account.getBalance());
        updateBalance(account.getAccountNumber(), actualBalance);
        return movementRepository.save(movement);
    }

    private void updateBalance(long accountNumber, BigDecimal balance) {
        Account accountBalance = new Account(balance);
        accountService.update(accountNumber, accountBalance);
    }

    private BigDecimal calculateBalance(BigDecimal initialBalance, BigDecimal transactionAmount, MovementTypeEnum movementTypeEnum, boolean reverse) {
        log.info("Inicio metodo calculateBalance " +
                "initialBalance {} - transactionAmount {} - movementType {}", initialBalance, transactionAmount, movementTypeEnum);
        if (movementTypeEnum.equals(MovementTypeEnum.DEPOSITO)) {
            if (reverse && initialBalance.compareTo(transactionAmount) < 0) {
                throw new CustomException(EnumError.ERROR_INSUFFICIENT_BALANCE.getMessage(), HttpStatus.BAD_REQUEST);
            } else if (reverse) {
                return initialBalance.subtract(transactionAmount);
            }
            return initialBalance.add(transactionAmount);
        } else if (movementTypeEnum.equals(MovementTypeEnum.RETIRO)) {
            if (initialBalance.compareTo(transactionAmount) < 0) {
                throw new CustomException(EnumError.ERROR_INSUFFICIENT_BALANCE.getMessage(), HttpStatus.BAD_REQUEST);
            } else if (reverse) {
                return initialBalance.add(transactionAmount);
            }
            return initialBalance.subtract(transactionAmount);
        } else {
            throw new CustomException(EnumError.ERROR_MOVEMENT_TYPE_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Movement findById(long id) {
        log.info("Inicio metodo findById {}", id);
        return movementRepository.findById(id)
                .orElseThrow(() -> new CustomException(EnumError.ERROR_MOVEMENT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @Override
    public List<Movement> findAll() {
        log.info("Inicio metodo findAll");
        List<Movement> movements = movementRepository.findAll();
        if (movements.isEmpty()) {
            throw new CustomException(EnumError.ERROR_MOVEMENTS_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return movements;
    }

    @Override
    public void delete(long id) {
        log.info("Inicio metodo update id {}", id);
        movementRepository.findById(id)
                .ifPresentOrElse(
                        movement -> {
                            BigDecimal actualBalance = calculateBalance(movement.getAccount().getBalance(), movement.getAmount(), movement.getMovementType(), true);
                            updateBalance(movement.getAccount().getAccountNumber(), actualBalance);
                            movementRepository.deleteById(id);
                        },
                        () -> {
                            throw new CustomException(EnumError.ERROR_MOVEMENT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
                        });
    }

    @Override
    public List<Movement> findByCriteria(LocalDate startDate, LocalDate endDate, long clientId) {
        log.info("Inicio metodo findByCriteria startDate {} - endDate {} - clientId {}", startDate, endDate, clientId);
        List<Movement> movements = movementRepository.findByDateRangeAndClientId(
                startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX), clientId);
        if (movements.isEmpty()) {
            throw new CustomException(EnumError.ERROR_MOVEMENTS_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return movements;
    }
}
