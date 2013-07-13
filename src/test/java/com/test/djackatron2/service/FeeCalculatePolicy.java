package com.test.djackatron2.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.test.djackatron2.impl.FeeCalculateImpl;

public class FeeCalculatePolicy {

	@Test
	public void TestCalculateFixFee() {
		//given
		double feeAmount = 7.0d;
		FeeCalculate service = new FeeCalculateImpl(feeAmount);
		
		//when
		double input,output;
		input = 150.0d;
		output = service.feeCalculate(input);

		//then
		assertTrue(output == feeAmount);
	}

}
