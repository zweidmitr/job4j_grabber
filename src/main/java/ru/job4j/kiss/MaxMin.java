package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return mini(value, comparator, i -> i < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return mini(value, comparator, i -> i > 0);
    }

    public <T> T mini(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T miniMax = value.get(0);
        for (T elem : value) {
            int tempComp = comparator.compare(miniMax, elem);
            miniMax = predicate.test(tempComp) ? elem : miniMax;
        }
        return miniMax;
    }
}
