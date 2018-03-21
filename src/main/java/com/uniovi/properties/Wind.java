package com.uniovi.properties;

public class Wind implements Property{

	private static final double MAX_VALUE = 30.0;
	private Double value;
	
	public Wind(Double value) {
		this.value = value;
	}
	
	@Override
	public boolean hasNormalValue() {
		if (this.value > MAX_VALUE)
			return false;
		return true;
	}

}
