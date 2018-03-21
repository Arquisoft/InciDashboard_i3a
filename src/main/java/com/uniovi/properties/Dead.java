package com.uniovi.properties;

public class Dead implements Property{
	
	private int value;
	
	public Dead(int value) {
		this.value = value;
	}
	
	@Override
	public boolean hasNormalValue() {
		if (this.value > 0)
			return false;
		return true;
	}

	
}
