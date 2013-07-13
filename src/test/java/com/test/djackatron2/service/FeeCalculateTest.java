package com.test.djackatron2.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.test.djackatron2.impl.FeeCalculateImpl;

public class FeeCalculateTest {

	@Test
	public void TestCalculateFixFee() {
		//given
		double feeAmount = 7.0d;
		double output;
		FeeCalculate service = new FeeCalculateImpl(feeAmount);
		
		//when
		output = service.feeCalculate(15.0d);
		//then
		assertTrue(output == feeAmount);

		//when
		output = service.feeCalculate(150.0d);
		//then
		assertTrue(output == feeAmount);

		//when
		output = service.feeCalculate(1500.0d);
		//then
		assertTrue(output == feeAmount);
	}

}
