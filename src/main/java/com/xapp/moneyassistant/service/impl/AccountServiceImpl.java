package com.xapp.moneyassistant.service.impl;

import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xapp.moneyassistant.model.Account;
import com.xapp.moneyassistant.model.AccountType;
import com.xapp.moneyassistant.model.Transaction;
import com.xapp.moneyassistant.repository.AccountRepository;
import com.xapp.moneyassistant.repository.AccountTypeRepository;
import com.xapp.moneyassistant.repository.TransactionRepository;
import com.xapp.moneyassistant.service.AccountService;
import com.xapp.moneyassistant.util.IdMismatchException;
import com.xapp.moneyassistant.util.ItemNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AccountTypeRepository accountTypeRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public Account create(Account c) {
		return accountRepository.save(c);
	}

	@Override
	public Account update(long id, Account c) {
		if (c.getId() != id) {
			throw new IdMismatchException();
		}
		accountRepository.findById(id).orElseThrow(ItemNotFoundException::new);
		return accountRepository.save(c);
	}
	
	private Double computeAccountValue(Account account) {
		Iterable<Transaction> transactions = transactionRepository.getTransactionsByAccountId(account);
		double incomes = 0, expenses = 0;
		Iterator<Transaction> iterator = transactions.iterator();
		while (iterator.hasNext()) {
			Transaction transaction = iterator.next();
			if (transaction.getType().equals(Transaction.TransactionType.INCOME)) {
				incomes += transaction.getAmount();
			} else {
				expenses += transaction.getAmount();
			}
		}
		return incomes - expenses;
	}

	@Override
	public Account findAccountById(long id) {
		Account account = accountRepository.findById(id).orElseThrow(ItemNotFoundException::new);
		double result = computeAccountValue(account);
		account.setCurrentAmount(account.getAmount() + result);
		return account;
	}

	@Override
	public void delete(long id) {
		accountRepository.findById(id).orElseThrow(ItemNotFoundException::new);
		accountRepository.deleteById(id);
	}

	@Override
	public Iterable<Account> getAccounts() {
		Iterable<Account> accounts = accountRepository.findAll();
		accounts.forEach(account -> account.setCurrentAmount(account.getAmount() + computeAccountValue(account)));
		return accounts;
	}

	@Override
	public Iterable<AccountType> getAccountTypes() {
		return accountTypeRepository.findAll();
	}

}
