package com.test.djackatron2.impl;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.service.AccountRepository;
import com.test.djackatron2.service.FeeCalculate;
import com.test.djackatron2.service.InsufficientFundException;
import com.test.djackatron2.service.OutOfServiceException;
import com.test.djackatron2.service.TimeService;
import com.test.djackatron2.service.TransferService;

public class TransferServiceImpl implements TransferService {

	private FeeCalculate feeAmount;
	private AccountRepository accountRepo;
	private double minimumTransfer;
	private TimeService timeService;
	
	@Override
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepo = accountRepository;
		
	}

	@Override
	public void setFeeAmount(FeeCalculate feeAmount) {
		this.feeAmount = feeAmount;
	}

	@Override
	public void transfer(double amountTransfer, long srcAccId, long desAccId) {
		double amount;
		double feeRate = this.feeAmount.feeCalculate(amountTransfer);
		
		//Handle tests
		if(amountTransfer <= 0d){
			throw new IllegalArgumentException();
		}
		if(amountTransfer < minimumTransfer){
			throw new IllegalArgumentException();
		}
		if(timeService!=null && !timeService.checkWorking()){
			throw new OutOfServiceException();
		}
			//Handle source account, get fee to include
		Account srcAcc = accountRepo.find(srcAccId);
		amount = srcAcc.getAmount() - (amountTransfer + feeRate);
		
		if(amount < 0d){
			throw new InsufficientFundException();
		}
		srcAcc.setAmount(amount);
			//Handle destination account, plus amountTransfer
		Account desAcc = accountRepo.find(desAccId);
		amount = desAcc.getAmount() + amountTransfer;
		desAcc.setAmount(amount);
	}

	@Override
	public void setMinimumTransferAmount(double amount) {
		this.minimumTransfer = amount;
	}

	@Override
	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;
		
	}

}
