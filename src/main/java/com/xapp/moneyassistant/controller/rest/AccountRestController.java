package com.xapp.moneyassistant.controller.rest;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.xapp.moneyassistant.model.Account;
import com.xapp.moneyassistant.model.StandardOutput;
import com.xapp.moneyassistant.service.AccountService;

@RestController
@RequestMapping(value = "/api/account")
public class AccountRestController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping(value = "/create")
	public Account createAccount(@RequestBody Account a) {
		a = accountService.create(a);
		return a;
	}
	
	@PostMapping(value = "/create/form")
	public StandardOutput createAccountForm(@ModelAttribute @Valid Account a, Errors e) {
		StandardOutput output = new StandardOutput();
		if (e.hasErrors()) {
			output.setStatus(false);
			Map<String, String> errors = e.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			output.setErrors(errors);
		} else {
			output.setStatus(true);
			a = accountService.create(a);
			output.setObject(a);
		}
		return output;
	}
	
	@PutMapping(value = "/update/form/{id}")
	public StandardOutput updateAccountForm(@PathVariable long id, @ModelAttribute @Valid Account a, Errors e) {
		StandardOutput output = new StandardOutput();
		if (e.hasErrors()) {
			output.setStatus(false);
			Map<String, String> errors = e.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			output.setErrors(errors);
		} else {
			output.setStatus(true);
			a = accountService.update(id, a);
			output.setObject(a);
		}
		return output;
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public Boolean deleteAccount(@PathVariable long id) {
		accountService.delete(id);
		return true;
	}
	
	@PutMapping(value = "/update/{id}")
	public Account updateAccount(@PathVariable long id, @RequestBody Account a) {
		a = accountService.update(id, a);
		return a;
	}
	
	@GetMapping(value = "/all")
	public Iterable<Account> getAccounts() {
		Iterable<Account> a = accountService.getAccounts();
		return a;
	}
	
}
