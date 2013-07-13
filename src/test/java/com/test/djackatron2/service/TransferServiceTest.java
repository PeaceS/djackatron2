package com.test.djackatron2.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.test.djackatron2.impl.TransferServiceImpl;
import com.test.djackatron2.model.Account;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class TransferServiceTest {

	@Test
	public void TestTransferService() {
		//given
		double feeRate = 5d;
		double amountTransfer = 10d;
		double expectedOutput = 85d;
		long srcAccId = 1;
		long desAccId = 2;
			//Set Account source
		Account srcAccount = new Account();
		srcAccount.setId(srcAccId);
		srcAccount.setAmount(100d);
			//Set Account destination
		Account desAccount = new Account();
		desAccount.setId(desAccId);
		desAccount.setAmount(0d);
			//Mock FeeCalciulate class, doesn't matter on input of feeCalculate, will return feeRate
		FeeCalculate feeAmount = mock(FeeCalculate.class);
		when(feeAmount.feeCalculate(anyDouble())).thenReturn(feeRate);
			//Mock AccountRepository class, fix each account for each input on find()
		AccountRepository accountRepository = mock(AccountRepository.class);
		when(accountRepository.find(srcAccId)).thenReturn(srcAccount);
		when(accountRepository.find(desAccId)).thenReturn(desAccount);
			//Implement TransferService, add FeeCalculate and AccountRepository object
		TransferService service = new TransferServiceImpl();
		service.setFeeAmount(feeAmount);
		service.setAccountRepository(accountRepository);
		
		//when
		service.transfer(amountTransfer, srcAccId, desAccId);
		
		//then
		assertThat(desAccount.getAmount(), equalTo(amountTransfer));
		assertThat(srcAccount.getAmount(), equalTo(expectedOutput));
	}

}
