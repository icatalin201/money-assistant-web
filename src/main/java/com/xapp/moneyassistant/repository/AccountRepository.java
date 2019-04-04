package com.xapp.moneyassistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xapp.moneyassistant.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
