package com.uniovi.properties;

public class Fire implements Property {

	private boolean value;

	public Fire(boolean value) {
		this.value = value;
	}
	
	@Override
	public boolean hasNormalValue() {
		if (value)
			return false;
		return true;
	}

}
