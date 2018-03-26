package com.uniovi.properties;

public class Wind implements Property {

	private static final double MAX_VALUE = 30.0;
	private Double value;

	public Wind(Double value) {
		checkValue(value);
		this.value = value;
	}

	private void checkValue(double val) {
		if (val < 0) {
			throw new IllegalArgumentException("The wind's speed should have a value greater or equal to 0");
		}
	}

	@Override
	public boolean hasNormalValue() {
		if (this.value > MAX_VALUE)
			return false;
		return true;
	}

}
