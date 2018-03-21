package com.uniovi.properties;

public class Robbery implements Property {

	private boolean value;

	public Robbery(boolean value) {
		this.value = value;
	}
	
	@Override
	public boolean hasNormalValue() {
		if (value)
			return false;
		return true;
	}
}
