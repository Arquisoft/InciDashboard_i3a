package com.uniovi.properties;

public class Temperature implements Property {

	private static final double MAX_VALUE = 40.0;
	private static final double MIN_VALUE = 0.0;
	private double value;

	public Temperature(double value) {
		this.value = value;
	}
	
	@Override
	public boolean hasNormalValue() {
		if (this.value > MAX_VALUE || this.value < MIN_VALUE)
			return false;
		return true;
	}

}
