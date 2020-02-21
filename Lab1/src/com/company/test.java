package com.company;
import org.junit.Test;
import static org.junit.Assert.*;

public class test {
    ICalculator calculator = new SimpleCalc();
    @Test
    public void testAdd(){
        assertEquals(5, calculator.add(2, 3));
        assertEquals(1,calculator.add(-2,3));
        assertEquals(-2,calculator.add(0,-2));
        assertEquals(5,calculator.add(5,0));
        assertEquals(5,calculator.add(2,3));
    }
    @Test
    public void testDivision () {
        assertEquals(2,calculator.divide(4,2),0.00f);
        assertEquals(2.5,calculator.divide(5,2),0.00f);
        assertEquals(-2,calculator.divide(4,-2),0.00f);
        assertEquals(-5,calculator.divide(-15,3),0.00f);
        assertEquals(17.5,calculator.divide(70,4),0.00f);

    }
    @Test (expected = java.lang.RuntimeException.class)
    public void testDivisionByZero () {
        calculator.divide(5,0);

    }
}
