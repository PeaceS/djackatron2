package com.test.djackatron2.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.test.djackatron2.impl.FeeCalculateImpl;

public class FeeRateCalculateTest {

	private FeeCalculate feeAmount;
	@Before
	public void setup() {
		// given
		feeAmount = new FeeCalculateImpl(0d);
	}

	@Test
	public void testTransferLessThanThousand() {
		// given
		double amountTransfer = 200d;
		double expectedFee = 0d;

		// when
		double result = feeAmount.feeCalculate(amountTransfer);

		// then
		assertThat(result, equalTo(expectedFee));
	}

	@Test
	public void testTransferLessThanMillion() {
		// given
		double amountTransfer = 1001d;
		double expectedFee = 10.01d;

		// when
		double result = feeAmount.feeCalculate(amountTransfer);

		// then
		assertThat(result, equalTo(expectedFee));
	}
	
	@Test
	public void testTransferAMillion() {
		// given
		double amountTransfer = 1000000d;
		double expectedFee = 10000d;

		// when
		double result = feeAmount.feeCalculate(amountTransfer);

		// then
		assertThat(result, equalTo(expectedFee));
	}

	@Test
	public void testTransferMoreThanMillion() {
		// given
		double amountTransfer = 1000001d;
		double expectedFee = 20000d;

		// when
		double result = feeAmount.feeCalculate(amountTransfer);

		// then
		assertThat(result, equalTo(expectedFee));
	}

}
