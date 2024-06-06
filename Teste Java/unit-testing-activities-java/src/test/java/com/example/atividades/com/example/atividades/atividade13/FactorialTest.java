package com.example.atividades.atividade13;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class FactorialTest {

    private final Factorial factorial = new Factorial();

    @Test
    public void testCalculateZero() {
        int result = factorial.calculate(0);
        assertEquals(1, result);
    }

    @Test
    public void testCalculatePositiveNumber() {
        int result = factorial.calculate(5);
        assertEquals(120, result);
    }

    @Test
    public void testCalculateOne() {
        int result = factorial.calculate(1);
        assertEquals(1, result);
    }

    @Test
    public void testCalculateLargeNumber() {
        int result = factorial.calculate(10);
        assertEquals(3628800, result);
    }

    @Test
    public void testCalculateNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> factorial.calculate(-1));
    }
}
