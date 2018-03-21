package com.uniovi.properties;

public class Attack implements Property {

	private boolean value;

	public Attack(boolean value) {
		this.value = value;
	}
	
	@Override
	public boolean hasNormalValue() {
		if (value)
			return false;
		return true;
	}
}
