package com.uniovi.properties;

public class Humidity implements Property{

	private static final double MAX_VALUE = 65.0;
	private static final double MIN_VALUE = 20.0;
	private double value;

	public Humidity(double value) {
		checkValue(value);
		this.value = value;
	}
	
	private void checkValue(double val){
		if(val < 0 || val > 100){
			throw new IllegalArgumentException("The value of humidity should be between 0% and 100%");
		}
	}
	
	@Override
	public boolean hasNormalValue() {
		if (this.value > MAX_VALUE || this.value < MIN_VALUE)
			return false;
		return true;
	}

}
