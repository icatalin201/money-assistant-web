package com.xapp.moneyassistant.service;

import java.time.LocalDate;
import com.xapp.moneyassistant.model.Transaction;

public interface TransactionService {

	Transaction create(Transaction t);
	Transaction update(long id, Transaction t);
	Transaction findTransactionById(long id);
	void delete(long id);
	Iterable<Transaction> getTransactions();
	Iterable<Transaction> getTransactionsBetweenDates(LocalDate date1, LocalDate date2);
}
