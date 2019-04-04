package com.xapp.moneyassistant.service.impl;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xapp.moneyassistant.model.Transaction;
import com.xapp.moneyassistant.repository.TransactionRepository;
import com.xapp.moneyassistant.service.TransactionService;
import com.xapp.moneyassistant.util.IdMismatchException;
import com.xapp.moneyassistant.util.ItemNotFoundException;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public Transaction create(Transaction t) {
		return transactionRepository.save(t);
	}

	@Override
	public Transaction update(long id, Transaction t) {
		if (id != t.getId()) {
			throw new IdMismatchException();
		}
		transactionRepository.findById(id).orElseThrow(ItemNotFoundException::new);
		return transactionRepository.save(t);
	}

	@Override
	public Transaction findTransactionById(long id) {
		return transactionRepository.findById(id).orElseThrow(ItemNotFoundException::new);
	}

	@Override
	public void delete(long id) {
		transactionRepository.findById(id).orElseThrow(ItemNotFoundException::new);
		transactionRepository.deleteById(id);
	}

	@Override
	public Iterable<Transaction> getTransactions() {
		return transactionRepository.findAll();
	}

	@Override
	public Iterable<Transaction> getTransactionsBetweenDates(LocalDate date1, LocalDate date2) {
		return transactionRepository.getTransactionsBetweenDates(date1, date2);
	}

}
