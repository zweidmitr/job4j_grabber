package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        T max = null;
        if (value.size() > 0) {
            max = value.get(0);
            for (T elem : value) {
                if (comparator.compare(max, elem) < 0) {
                    max = elem;
                }
            }
        }
        return max;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        T min = null;
        if (value.size() > 0) {
            min = value.get(0);
            for (T elem : value) {
                if (comparator.compare(min, elem) > 0) {
                    min = elem;
                }
            }
        }
        return min;
    }
}
