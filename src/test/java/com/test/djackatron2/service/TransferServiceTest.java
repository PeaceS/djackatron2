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
		double amountTransfer = 30.0d;
		long srcAccId = 1;
		long desAccId = 2;
		
		Account srcAccount = new Account();
		srcAccount.setId(srcAccId);
		srcAccount.setAmount(100d);
		
		Account desAccount = new Account();
		desAccount.setId(desAccId);
		desAccount.setAmount(0d);
		
		FeeCalculate feeAmount = mock(FeeCalculate.class);
		when(feeAmount.feeCalculate(anyDouble())).thenReturn(feeRate);
		
		AccountRepository accountRepository = mock(AccountRepository.class);
		when(accountRepository.find(srcAccId)).thenReturn(srcAccount);
		when(accountRepository.find(desAccId)).thenReturn(desAccount);
		
		TransferService service = new TransferServiceImpl();
		service.setFeeAmount(feeAmount);
		service.setAccountRepository(accountRepository);
		
		//when
		service.transfer(amountTransfer, srcAccId, desAccId);
		
		//then
		assertThat(desAccount.getAmount(), equalTo(amountTransfer));
		assertThat(srcAccount.getAmount(), equalTo(65d));
	}

}
