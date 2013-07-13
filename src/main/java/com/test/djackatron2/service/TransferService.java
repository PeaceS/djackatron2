package com.test.djackatron2.service;


public interface TransferService {

	void transfer(double d, long srcAccId, long desAccId);

	void setAccountRepository(AccountRepository accountRepository);

	void setFeeAmount(FeeCalculate feeAmount);

}
