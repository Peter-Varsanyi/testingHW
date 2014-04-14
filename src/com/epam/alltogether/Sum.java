package com.epam.alltogether;


public class Sum extends AbstractCalculator {

    @Override
    protected Integer doCalcualte(Integer a, Integer b) {
        Integer result = a + b;
        return result;
    }
}
