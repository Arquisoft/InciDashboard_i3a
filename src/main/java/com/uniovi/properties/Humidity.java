package com.uniovi.properties;

public class Humidity implements Property{

	private static final double MAX_VALUE = 65.0;
	private static final double MIN_VALUE = 20.0;
	private double value;

	public Humidity(double value) {
		this.value = value;
	}
	
	@Override
	public boolean hasNormalValue() {
		if (this.value > MAX_VALUE || this.value < MIN_VALUE)
			return false;
		return true;
	}

}
