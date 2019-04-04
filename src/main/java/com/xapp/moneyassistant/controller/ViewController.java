package com.xapp.moneyassistant.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xapp.moneyassistant.model.Account;
import com.xapp.moneyassistant.model.AccountType;
import com.xapp.moneyassistant.model.Category;
import com.xapp.moneyassistant.model.Transaction;
import com.xapp.moneyassistant.service.AccountService;
import com.xapp.moneyassistant.service.CategoryService;
import com.xapp.moneyassistant.service.TransactionService;


@Controller
public class ViewController {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = {"/transactions"}, method = RequestMethod.GET)
	public ModelAndView getTransactions() {
		ModelAndView mv = new ModelAndView("transactions");
		LocalDate initial = LocalDate.now();
		LocalDate start = initial.withDayOfMonth(1);
		LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
		Iterable<Transaction> items = transactionService.getTransactionsBetweenDates(start, end);
		Iterable<Account> accounts = accountService.getAccounts();
		Iterable<Category> categories = categoryService.findAllByType("income");
		mv.addObject("items", items);
		mv.addObject("accounts", accounts);
		mv.addObject("categories", categories);
		return mv;
	}
	
	@RequestMapping(value = "/transactions/{id}", method = RequestMethod.GET)
	public ModelAndView getTransaction(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("edit_transaction");
		Transaction item = transactionService.findTransactionById(id);
		mv.addObject("item", item);
		return mv;
	}
	
	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public ModelAndView getAccounts() {
		ModelAndView mv = new ModelAndView("accounts");
		Iterable<Account> accounts = accountService.getAccounts();
		Iterable<AccountType> accountTypes = accountService.getAccountTypes();
		mv.addObject("accounts", accounts);
		mv.addObject("account_types", accountTypes);
		return mv;
	}
	
	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
	public ModelAndView getAccount(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("edit_account");
		Account account = accountService.findAccountById(id);
		mv.addObject("account", account);
		return mv;
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ModelAndView getCategories() {
		ModelAndView mv = new ModelAndView("categories");
		Iterable<Category> categories = categoryService.getCategories();
		mv.addObject("categories", categories);
		return mv;
	}
	
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public ModelAndView getCategory(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("edit_category");
		Category category = categoryService.findCategoryById(id);
		mv.addObject("category", category);
		return mv;
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public ModelAndView getSettings() {
		ModelAndView mv = new ModelAndView("settings");
		return mv;
	}
	
}
