package com.uniovi.properties;

public class Wounded implements Property {

	private int value;

	public Wounded(int value) {
		checkValue(value);
		this.value = value;
	}

	private void checkValue(int value2) {
		if (value2 < 0) {
			throw new IllegalArgumentException("The number of wounded should be 0, or more");
		}
	}

	@Override
	public boolean hasNormalValue() {
		if (this.value > 0)
			return false;
		return true;
	}

}
