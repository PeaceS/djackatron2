package com.test.djackatron2.service;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

import com.test.djackatron2.impl.FeeCalculateImpl;

public class FeeCalculateTest {

	@Test
	public void TestCalculateFixFee() {
		//given
		double feeAmount = 0d;
		double output;
		FeeCalculate service = new FeeCalculateImpl(feeAmount);
		
		//when
		output = service.feeCalculate(15d);
		//then
		assertThat(output, equalTo(feeAmount));

		//when
		output = service.feeCalculate(150d);
		//then
		assertThat(output, equalTo(feeAmount));

		//when
		feeAmount = 1500*0.01;
		output = service.feeCalculate(1500d);
		//then
		assertThat(output, equalTo(feeAmount));
	}

}
