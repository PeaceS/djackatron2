package com.test.djackatron2.impl;

import com.test.djackatron2.service.FeeCalculate;

public class FeeCalculateImpl implements FeeCalculate {

	private double feeAmount;
	public FeeCalculateImpl(double feeAmount) {
		this.feeAmount = feeAmount;
	}

	@Override
	public double feeCalculate(double input) {
		double output;
		if(input <= 1000d){
			output = 0d;
		}else if(input <= 1000000d){
			output = input * 0.01;
		}else{
			output = 20000d;
		}
		return output;
	}

}
