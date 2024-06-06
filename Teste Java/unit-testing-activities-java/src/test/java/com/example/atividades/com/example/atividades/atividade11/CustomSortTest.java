package com.example.atividades.atividade11;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CustomSortTest {

    private final CustomSort customSort = new CustomSort();

    @Test
    public void testCustomSortWithPositiveNumbers() {
        List<Integer> numbers = Arrays.asList(1, 3, 2, 5, 4);
        List<Integer> sortedNumbers = customSort.customSort(numbers);
        assertEquals(Arrays.asList(5, 4, 3, 2, 1), sortedNumbers);
    }

    @Test
    public void testCustomSortWithNegativeNumbers() {
        List<Integer> numbers = Arrays.asList(-1, -3, -2, -5, -4);
        List<Integer> sortedNumbers = customSort.customSort(numbers);
        assertEquals(Arrays.asList(-1, -2, -3, -4, -5), sortedNumbers);
    }

    @Test
    public void testCustomSortWithMixedNumbers() {
        List<Integer> numbers = Arrays.asList(1, -3, 2, -5, 4);
        List<Integer> sortedNumbers = customSort.customSort(numbers);
        assertEquals(Arrays.asList(4, 2, 1, -3, -5), sortedNumbers);
    }

    @Test
    public void testCustomSortWithEmptyList() {
        List<Integer> numbers = Collections.emptyList();
        List<Integer> sortedNumbers = customSort.customSort(numbers);
        assertEquals(Collections.emptyList(), sortedNumbers);
    }

    @Test
    public void testCustomSortWithSingleElement() {
        List<Integer> numbers = Collections.singletonList(1);
        List<Integer> sortedNumbers = customSort.customSort(numbers);
        assertEquals(Collections.singletonList(1), sortedNumbers);
    }
}
