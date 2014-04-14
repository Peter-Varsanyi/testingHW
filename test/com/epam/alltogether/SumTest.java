package com.epam.alltogether;

import org.junit.Assert;
import org.junit.Test;

public class SumTest {
	
	@Test
	public void testdoCalculateShouldReturnFiveWhenParametersAreTwoAndThree() {
		// GIVEN
		Sum underTest = new Sum();
		Integer two  = 2;
		Integer three = 3;
		// WHEN
		Integer result = underTest.doCalcualte(two, three);
		// THEN
		Assert.assertEquals("Sum of 2 and 3 should be: 5", Integer.valueOf(5), result);
		
	}
	
}
