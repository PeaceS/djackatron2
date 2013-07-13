package com.test.djackatron2.service;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.*;

import static org.junit.Assert.*;

import com.test.djackatron2.impl.TransferServiceImpl;
import com.test.djackatron2.model.Account;

public class AmountTransferServiceTest {
	
	private TransferService service = null;
	private long srcAccId, desAccId;
	
	@Before
	public void setup(){
		//given
		double feeRate = 5d;
		srcAccId = 1;
		desAccId = 2;
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
		service = new TransferServiceImpl();
		service.setFeeAmount(feeAmount);
		service.setAccountRepository(accountRepository);
		
	}
	
	@Test(expected=InsufficientFundException.class)
	public void testTransferWhenInsufficientFund(){
		//given
		double amountTransfer = 200d;
		
		//when
		service.transfer(amountTransfer, srcAccId, desAccId);
		
		//then
		fail();
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTransferWhenZeroAmountTransfer(){
		//given
		double amountTransfer = 0d;
		
		//when
		service.transfer(amountTransfer, srcAccId, desAccId);
		
		//then
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTransferWhenNegativeAmountTransfer(){
		//given
		double amountTransfer = -10d;
		
		//when
		service.transfer(amountTransfer, srcAccId, desAccId);
		
		//then
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetMinimumTransferAmount() {
		//given
		double amountTransfer = 10d;
		double minimumTransfer = 20d;

		//when
		service.setMinimumTransferAmount(minimumTransfer);
		service.transfer(amountTransfer, srcAccId, desAccId);
		
		//then
		fail();
	}
}
