package com.company;

public class SimpleCalc implements ICalculator {
    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public float divide(int x, int y) throws RuntimeException {
        if(y == 0) throw new RuntimeException("You cannot divide by zero");
        else return (float) x / y;
    }
}
