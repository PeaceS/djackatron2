package com.test.djackatron2.impl;

import java.util.HashMap;
import java.util.Map;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.service.AccountRepository;

public class DummyAccountRepository implements AccountRepository {

	private Map<Long, Account> accounts;
	
	public DummyAccountRepository(){
		accounts = new HashMap<Long, Account>();
		for(long i=0;i<10;i++){
			accounts.put(i, new Account(i, "name"+i, 100d));
		}
	}
	
	@Override
	public Account find(long srcAccId) {
		return accounts.get(srcAccId);
	}

	@Override
	public Account create(Account account) {
		accounts.put(account.getId(), account);
		return account;
	}

	@Override
	public Account update(Account account) {
		accounts.put(account.getId(), account);
		return account;
	}

	@Override
	public Account delete(Long accId) {
		return accounts.remove(accId);
	}

}
