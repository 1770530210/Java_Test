package gui.generic;

import java.time.LocalDate;

public class PairTest2 {
    public static void main(String[] args) {
        LocalDate[] birthdays = {
                LocalDate.of(1906, 12, 9),
                LocalDate.of(1815, 12, 10),
                LocalDate.of(1903, 12, 3),
                LocalDate.of(1910, 6, 22)
        };
        Pair<LocalDate> mm = ArrayAlg2.minMax(birthdays);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }
}

class ArrayAlg2 {
    public static <T extends Comparable> Pair<T> minMax(T[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        T min = args[0];
        T max = args[0];
        for (T arg : args) {
            if (min.compareTo(arg) > 0) {
                min = arg;
            }
            if (max.compareTo(arg) < 0) {
                max = arg;
            }
        }
        return new Pair<>(min, max);
    }
}