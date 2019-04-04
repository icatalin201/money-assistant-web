package com.xapp.moneyassistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xapp.moneyassistant.model.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
	
}
