package com.test.djackatron2.service;

import com.test.djackatron2.model.Account;

public interface AccountRepository {

	Account find(long srcAccId);

	Account create(Account account);

	Account update(Account account);

	Account delete(Long accId);

}
