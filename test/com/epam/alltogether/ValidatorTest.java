package com.epam.alltogether;

import org.junit.Test;

public class ValidatorTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateNotNullShouldThrowExceptionWhenParameterIsNull() {
		// GIVEN
		Object input = null;
		// WHEN
		Validator.validateNotNull(input);
		// THEN throw Exception
	}
	
	@Test
	public void testValidateNotNullShouldNotThrowExceptionWhenParameterIsString() {
		// GIVEN
		String input = new String();
		// WHEN
		Validator.validateNotNull(input);
		// THEN do not throw Exception
	}
	
}
