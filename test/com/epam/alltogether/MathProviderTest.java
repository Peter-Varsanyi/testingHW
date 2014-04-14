package com.epam.alltogether;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class MathProviderTest {
	
	private MathProvider	underTest;
	
	@Before
	public void setUp() {
		underTest = new MathProvider();
		AbstractCalculator sum = new Sum();
		underTest.setCalc(sum);
	}
	
	@Test
	public void testSumShouldReturnSixWhenThreeTimesTwoAreGivenAsAList() {
		// GIVEN
		List<Integer> input = new ArrayList<>(Arrays.asList(2,2,2));
		AbstractCalculator calculator = new Sum();
		//WHEN
		String result = underTest.sum(input);
		//THEN
		Assert.assertEquals("[6]", result);
	}
}
