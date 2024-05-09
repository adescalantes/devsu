package com.devsu.accountservice.service;

import com.devsu.accountservice.model.entity.Account;

import java.util.List;

public interface IAccountService {
    Account save(Account client);

    Account findByAccountNumber(long accountNumber);

    List<Account> findAll(Boolean statusEnum);

    Account update(long accountNumber, Account account);

    void delete(long accountNumber);

    Account findValidAccount(long accountNumber);
}
