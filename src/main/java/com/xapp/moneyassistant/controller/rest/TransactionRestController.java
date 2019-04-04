package com.xapp.moneyassistant.controller.rest;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xapp.moneyassistant.model.StandardOutput;
import com.xapp.moneyassistant.model.Transaction;
import com.xapp.moneyassistant.service.TransactionService;

@RestController
@RequestMapping(value = "/api/transaction")
public class TransactionRestController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping(value = "/create")
	public Transaction createTransaction(@RequestBody Transaction t) {
		t = transactionService.create(t);
		return t;
	}
	
	@PostMapping(value = "/create/form")
	public StandardOutput createTransactionForm(@ModelAttribute @Valid Transaction t, Errors error) {
		StandardOutput output = new StandardOutput();
		if (error.hasErrors()) {
			output.setStatus(false);
			Map<String, String> errors = error.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			output.setErrors(errors);
		} else {
			output.setStatus(true);
			t = transactionService.create(t);
			output.setObject(t);
		}
		return output;
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public Boolean deleteTransaction(@PathVariable long id) {
		transactionService.delete(id);
		return true;
	}
	
	@PutMapping(value = "/update/{id}")
	public Transaction updateTransaction(@PathVariable long id, @RequestBody Transaction t) {
		t = transactionService.update(id, t);
		return t;
	}
	
	@PutMapping(value = "/update/form/{id}")
	public StandardOutput updateTransactionForm(@PathVariable long id, @ModelAttribute @Valid Transaction t, Errors error) {
		StandardOutput output = new StandardOutput();
		if (error.hasErrors()) {
			output.setStatus(false);
			Map<String, String> errors = error.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			output.setErrors(errors);
		} else {
			output.setStatus(true);
			t = transactionService.update(id, t);
			output.setObject(t);
		}
		return output;
	}
	
	@GetMapping(value = "/all")
	public Iterable<Transaction> getAllTransactions() {
		return transactionService.getTransactions();
	}
	
	@GetMapping(value = "/interval")
	public Iterable<Transaction> getTransactionsBetween(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date1, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date2) {
		Iterable<Transaction> t = transactionService.getTransactionsBetweenDates(date1, date2);
		return t;
	}
	
}
