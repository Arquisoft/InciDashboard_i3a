package com.uniovi.properties;

public class Wind implements Property{

	private String value;
	
	public Wind(String value) {
		this.value = value;
	}
	
	@Override
	public boolean hasNormalValue() {
		if (value.toLowerCase().contains("strong")) 
			return false;
		return true;
	}

}
