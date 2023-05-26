package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;

public class StreamApi {
    public static void main(String[] args) {
        int[] numbers = {5, 4, 8, 1, 9, 4, 2, 1, 3, 6, 8};
        System.out.println(minValue(numbers));

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 4);
        System.out.println(oddOrEven(integers));
    }

    static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (result, digit) -> result * 10 + digit);
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream()
                .reduce(0, Integer::sum);

        return integers.stream()
                .filter(number -> (sum % 2 == 0) == (number % 2 != 0))
                .toList();
    }
}
