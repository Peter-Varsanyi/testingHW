package com.epam.alltogether;

public abstract class AbstractCalculator implements Calculator {
    private void validateInput(Integer a, Integer b) {
        Validator.validateNotNull(a);
        Validator.validateNotNull(b);
    }

    public final Integer calculate(Integer a, Integer b) {
        validateInput(a, b);
        
        return doCalcualte(a, b);
    }

    protected abstract Integer doCalcualte(Integer a, Integer b);
}
