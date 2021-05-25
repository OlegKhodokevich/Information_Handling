package by.khodokevich.composite.service;

import java.math.BigDecimal;
import java.util.*;

public abstract class A {
    public static int x = 2;

    private static final String EXPRESSION_DELIMITER_REGEX = "(?<=\\d)(?=[&|^>{2,3}<{3}])|(?<=[&|^>{2,3}<{3}])(?=\\d)";

    public static void main(String[] args) {
        System.out.println(new BigDecimal("0.15"));
        List<Integer> integerList = new ArrayList<>();
        integerList.add(5);
        Optional<Integer> integer = integerList.stream().min(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        System.out.println(integer.get());
        String [] numbers = "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)".split(EXPRESSION_DELIMITER_REGEX);

        System.out.println(Arrays.toString(numbers));

    }
    public abstract void m();
}
