package com.uniovi.properties;

public class Dead implements Property {

	private int value;

	public Dead(int value) {
		checkValue(value);
		this.value = value;
	}

	public void checkValue(int value2) {
		if (value < 0) {
			throw new IllegalArgumentException("The number of dead should be 0 or more");
		}
	}

	@Override
	public boolean hasNormalValue() {
		if (this.value > 0)
			return false;
		return true;
	}

}
