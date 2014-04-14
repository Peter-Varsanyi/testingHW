package com.epam.alltogether;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractCalculatorThroughSumTest {
	
	private Sum	underTest;
	
	@Before
	public void setUp() {
		underTest = new Sum();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateShouldThrowExceptionWhenCalledWithNilAsFirstParameter() {
		// GIVEN
		Integer input1 = 5;
		// WHEN
		underTest.calculate(input1, null);
		// THEN throw Exception
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateShouldThrowExceptionWhenCalledWithNilAsSecondParameter() {
		// GIVEN
		Integer input2 = 5;
		// WHEN
		underTest.calculate(null, input2);
		// THEN throw Exception
	}
	
	@Test
	public void testdoCalculateShouldReturnFiveWhenParametersAreTwoAndThree() {
		underTest = new Sum();
		Integer two = 2;
		Integer three = 3;
		// WHEN
		Integer result = underTest.calculate(two, three);
		// THEN
		Assert.assertEquals("Sum of 2 and 3 should be: 5", Integer.valueOf(5), result);
		
	}
}
