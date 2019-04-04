package com.xapp.moneyassistant.service;

import com.xapp.moneyassistant.model.Account;
import com.xapp.moneyassistant.model.AccountType;

public interface AccountService {

	Account create(Account c);
	Account update(long id, Account c);
	Account findAccountById(long id);
	void delete(long id);
	Iterable<Account> getAccounts();
	Iterable<AccountType> getAccountTypes();
	
}
