package com.uniovi.properties;

public class Wounded implements Property{
	
	private int value;
	
	public Wounded(int value) {
		this.value = value;
	}
	
	@Override
	public boolean hasNormalValue() {
		if (this.value > 0)
			return false;
		return true;
	}

	
}
