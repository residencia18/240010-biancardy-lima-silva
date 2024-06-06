package com.example.atividades.atividade08;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class StatisticsTest {

    private final Statistics statistics = new Statistics();

    @Test
    public void testCalculateAverageWithValidList() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        double result = statistics.calculateAverage(numbers);
        assertEquals(3.0, result, 0.0001);
    }

    @Test
    public void testCalculateAverageWithSingleElement() {
        List<Integer> numbers = Collections.singletonList(10);
        double result = statistics.calculateAverage(numbers);
        assertEquals(10.0, result, 0.0001);
    }

    @Test
    public void testCalculateAverageWithNegativeNumbers() {
        List<Integer> numbers = Arrays.asList(-1, -2, -3, -4, -5);
        double result = statistics.calculateAverage(numbers);
        assertEquals(-3.0, result, 0.0001);
    }

    @Test
    public void testCalculateAverageWithMixedNumbers() {
        List<Integer> numbers = Arrays.asList(1, -1, 1, -1, 1);
        double result = statistics.calculateAverage(numbers);
        assertEquals(0.2, result, 0.0001);
    }

    @Test
    public void testCalculateAverageWithEmptyList() {
        List<Integer> numbers = Collections.emptyList();
        assertThrows(IllegalArgumentException.class, () -> statistics.calculateAverage(numbers));
    }

    @Test
    public void testCalculateAverageWithNullList() {
        assertThrows(IllegalArgumentException.class, () -> statistics.calculateAverage(null));
    }
}
