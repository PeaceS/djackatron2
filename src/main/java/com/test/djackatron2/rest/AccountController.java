package com.test.djackatron2.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.service.AccountRepository;

@Controller
@RequestMapping("/account")
public class AccountController {

	private AccountRepository repository;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Account getOne(Long accId) {
		return repository.find(accId);
	}

	public void setAccountRepository(AccountRepository repository) {
		this.repository = repository;
		
	}

	public Account save(Account account) {
		return repository.create(account);
	}

	public Account update(Account account) {
		return repository.update(account);
	}

	public Account delete(Long accId) {
		return repository.delete(accId);
	}
}
