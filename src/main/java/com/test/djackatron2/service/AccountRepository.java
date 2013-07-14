package com.test.djackatron2.service;

import com.test.djackatron2.model.Account;

public interface AccountRepository {

	public Account find(long srcAccId);

	public Account create(Account account);

	public Account update(Account account);

	public Account delete(Long accId);

}
