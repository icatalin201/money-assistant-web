package com.xapp.moneyassistant.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xapp.moneyassistant.model.Account;
import com.xapp.moneyassistant.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query(value = "select t from Transaction t where t.date >= ?1 and t.date <= ?2 order by t.id desc")
	Iterable<Transaction> getTransactionsBetweenDates(LocalDate date1, LocalDate date2);
	
	@Query(value = "select t from Transaction t where t.account = ?1")
	Iterable<Transaction> getTransactionsByAccountId(Account account);

}
