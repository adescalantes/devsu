package com.devsu.accountservice.service.implement;

import com.devsu.accountservice.api.ClientApi;
import com.devsu.accountservice.exception.CustomException;
import com.devsu.accountservice.model.entity.Account;
import com.devsu.accountservice.model.entity.Client;
import com.devsu.accountservice.repository.AccountRepository;
import com.devsu.accountservice.service.IAccountService;
import com.devsu.accountservice.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.devsu.accountservice.exception.EnumError.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final ClientApi clientApi;

    @Override
    public Account save(Account account) {
        log.info("Inicio metodo save {}", account);
        Client client = findAndValidateClient(account.getClient().getId());
        
        account.setAccountNumber(null);
        account.setClient(client);
        return accountRepository.save(account);
    }

    @Override
    public Account findByAccountNumber(long accountNumber) {
        log.info("Inicio metodo findById {}", accountNumber);
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new CustomException(ERROR_ACCOUNT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @Override
    public List<Account> findAll(Boolean status) {
        log.info("Inicio metodo findAll {}", status);

        return Optional.ofNullable(status)
                .map(statusClient -> {
                    List<Account> accounts = accountRepository.findAllByStatus(statusClient);
                    if (accounts.isEmpty()) {
                        throw new CustomException(ERROR_ACCOUNTS_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
                    }
                    return accounts;
                })
                .orElseGet(() -> {
                    List<Account> accounts = accountRepository.findAll();
                    if (accounts.isEmpty()) {
                        throw new CustomException(ERROR_ACCOUNTS_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
                    }
                    return accounts;
                });
    }

    @Override
    public Account update(long accountNumber, Account account) {
        log.info("Inicio metodo update accountNumber {} {}", accountNumber, account);
        return accountRepository.findByAccountNumber(accountNumber)
                .map(accountExist -> {
                    if (Objects.nonNull(account.getClient()) && Objects.nonNull(account.getClient().getId())) {
                        Client client = findAndValidateClient(account.getClient().getId());
                        account.setClient(client);
                    }
                    account.setAccountNumber(null);
                    Util.copyNonNullProperties(accountExist, account);
                    return accountRepository.save(accountExist);
                })
                .orElseThrow(() -> new CustomException(ERROR_ACCOUNT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @Override
    public void delete(long accountNumber) {
        log.info("Inicio metodo delete {}", accountNumber);
        accountRepository.findById(accountNumber)
                .ifPresentOrElse(
                        account -> {
                            if (!account.isStatus()) {
                                throw new CustomException(ERROR_ACCOUNT_STATUS_INACTIVE.getMessage(), HttpStatus.BAD_REQUEST);
                            }
                            account.setStatus(false);
                            accountRepository.save(account);
                        },
                        () -> {
                            throw new CustomException(ERROR_ACCOUNT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
                        });
    }
    
    private Client findAndValidateClient(long id) {
        Client client = clientApi.findClientById(id);
        
        if (!client.isStatus()) {
            throw new CustomException(ERROR_CLIENT_STATUS_INACTIVE.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return client;
    }

    public Account findValidAccount(long accountNumber) {
        log.info("Inicio metodo findById {}", accountNumber);
        return accountRepository.findByAccountNumber(accountNumber)
                .map(account -> {
                    findAndValidateClient(account.getClient().getId());
                    if(!account.isStatus()) {
                        throw new CustomException(ERROR_ACCOUNT_STATUS_INACTIVE.getMessage(), HttpStatus.BAD_REQUEST);
                    }
                    return account;
                })
                .orElseThrow(() -> new CustomException(ERROR_ACCOUNT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST));
    }
}
