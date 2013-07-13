package com.test.djackatron2.impl;

import com.test.djackatron2.service.FeeCalculate;

public class FeeCalculateImpl implements FeeCalculate {

	private double feeAmount;
	public FeeCalculateImpl(double feeAmount) {
		// TODO Auto-generated constructor stub
		this.feeAmount = feeAmount;
	}

	@Override
	public double feeCalculate(double input) {
		// TODO Auto-generated method stub
		return this.feeAmount;
	}

}
