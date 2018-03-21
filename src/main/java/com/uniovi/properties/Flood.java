package com.uniovi.properties;

public class Flood implements Property {

private boolean value;
	
	public Flood(boolean value) {
		this.value = value;
	}
	
	@Override
	public boolean hasNormalValue() {
		if (value)
			return false;
		return true;
	}
}
