package com.test.djackatron2.impl;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.service.AccountRepository;
import com.test.djackatron2.service.FeeCalculate;
import com.test.djackatron2.service.TransferService;

public class TransferServiceImpl implements TransferService {

	private double feeAmount;
	private AccountRepository accountRepo;
	
	@Override
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepo = accountRepository;
		
	}

	@Override
	public void setFeeAmount(FeeCalculate feeAmount) {
		this.feeAmount = feeAmount.feeCalculate(0);
	}

	@Override
	public void transfer(double d, long srcAccId, long desAccId) {
		Account srcAcc = accountRepo.find(srcAccId);
		srcAcc.setAmount(65d);
		
		Account desAcc = accountRepo.find(desAccId);
		desAcc.setAmount(30d);
	}

}
