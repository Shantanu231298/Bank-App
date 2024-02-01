package org.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wallet.model.Account;
import org.wallet.repository.AccountRepository;

@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Account saveAccount(Account account) {
		
		return accountRepository.save(account);
	}

}
