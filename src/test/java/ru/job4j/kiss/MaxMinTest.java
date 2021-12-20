package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {
    @Test
    public void whenMax() {
        MaxMin maxi = new MaxMin();
        int result = maxi.max(List.of(15, 45, 32, 4), Comparator.comparingInt(x -> x));
        int expected = 45;
        assertEquals(expected, result);
    }

    @Test
    public void whenMin() {
        MaxMin mini = new MaxMin();
        int result = mini.min(List.of(18, 22, 25, 29, -5, 7), Comparator.comparingInt(x -> x));
        int expected = -5;
        assertEquals(expected, result);
    }

}