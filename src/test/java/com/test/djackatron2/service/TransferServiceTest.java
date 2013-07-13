package com.test.djackatron2.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.test.djackatron2.impl.TransferServiceImpl;
import com.test.djackatron2.model.Account;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class TransferServiceTest {

	private TransferService service = null;
	private long srcAccId, desAccId;
	private Account srcAccount, desAccount;
	
	@Before
	public void setup(){
		//given
		double feeRate = 5d;
		srcAccId = 1;
		desAccId = 2;
		//Set Account source
		srcAccount = new Account();
		srcAccount.setId(srcAccId);
		srcAccount.setAmount(100d);
			//Set Account destination
		desAccount = new Account();
		desAccount.setId(desAccId);
		desAccount.setAmount(0d);
			//Mock FeeCalciulate class, doesn't matter on input of feeCalculate, will return feeRate
		FeeCalculate feeAmount = mock(FeeCalculate.class);
		when(feeAmount.feeCalculate(anyDouble())).thenReturn(feeRate);
			//Mock AccountRepository class, fix each account for each input on find()
		AccountRepository accountRepository = mock(AccountRepository.class);
		when(accountRepository.find(srcAccId)).thenReturn(srcAccount);
		when(accountRepository.find(desAccId)).thenReturn(desAccount);
			//Mock TimeService class, always return true, for method checkWorking()
		TimeService timeService = mock(TimeService.class);
		when(timeService.checkWorking()).thenReturn(true);
			//Implement TransferService, add FeeCalculate and AccountRepository object
		service = new TransferServiceImpl();
		service.setFeeAmount(feeAmount);
		service.setAccountRepository(accountRepository);
		service.setTimeService(timeService);
		
	}
	
	@Test
	public void testNormalTransferService() {
		//given
		double amountTransfer = 10d;
		double expectedOutput = 85d;
		
		//when
		service.transfer(amountTransfer, srcAccId, desAccId);
				
		//then
		assertThat(desAccount.getAmount(), equalTo(amountTransfer));
		assertThat(srcAccount.getAmount(), equalTo(expectedOutput));
	}
	
	@Test
	public void testTransferWithTimeService() {
		//given
		double amountTransfer = 10d;
		double expectedOutput = 85d;
		
		TimeService timeService = mock(TimeService.class);
		when(timeService.checkWorking()).thenReturn(true);
		service.setTimeService(timeService);
		
		//when
		service.transfer(amountTransfer, srcAccId, desAccId);
		
		//then
		verify(timeService).checkWorking();
		assertThat(desAccount.getAmount(), equalTo(amountTransfer));
		assertThat(srcAccount.getAmount(), equalTo(expectedOutput));
	}

	@Test(expected=OutOfServiceException.class)
	public void testTransferWithTimeOutOfService() {
		//given
		double amountTransfer = 10d;
		
		TimeService timeService = mock(TimeService.class);
		when(timeService.checkWorking()).thenReturn(false);
		service.setTimeService(timeService);
		
		//when
		service.transfer(amountTransfer, srcAccId, desAccId);
		
		//then
		verify(timeService).checkWorking();
		fail();
	}
}
