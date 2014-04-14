package com.epam.alltogether;

import java.util.List;
import java.util.logging.Logger;

public class MathProvider {
	private static final Logger	log	= Logger.getLogger(MathProvider.class.getName());
	private AbstractCalculator	calc;
	
	public String sum(List<Integer> numbers) {
		log.info("Calculating sum of a list of number");
		Integer result = 0;
		for (Integer number : numbers) {
			result = calc.calculate(result, number);
		}
		log.info("Calculation finished! Result is " + result);
		NumberFormatter formatter = new NumberFormatter();
		return formatter.format(result);
	}
	
	public void setCalc(AbstractCalculator calc) {
		this.calc = calc;
	}
}
