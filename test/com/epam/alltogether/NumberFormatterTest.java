package com.epam.alltogether;

import junit.framework.Assert;

import org.junit.Test;

public class NumberFormatterTest {
	
	@Test
	public void testFormatShouldReturnSpecificStringWhenCalledWithNumber() {
		// GIVEN
		NumberFormatter underTest = new NumberFormatter();
		Integer input = 5;
		// WHEN
		String result = underTest.format(input);
		Assert.assertEquals("[5]", result);
		
	}
	
}
