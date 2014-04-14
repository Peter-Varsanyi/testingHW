package com.epam.alltogether;

public class Validator {
	
	private Validator() {
	}
	
	public static void validateNotNull(Object input) {
		if (input == null) {
			throw new IllegalArgumentException("Null parameter is illegal!");
		}
	}
}
