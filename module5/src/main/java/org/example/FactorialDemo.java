package org.example;

import java.util.ArrayList;
import java.util.List;

public class FactorialDemo {
    public static List<Integer> factorialSubresults = new ArrayList<>();

    static {
        factorialSubresults.add(1);
        factorialSubresults.add(1);
    }

    // 5! = 5 * 4 * 3 * 2 * 1
    public static void main(String[] args) {
        System.out.println(getFactorialValue(5));
        System.out.println(getRecursiveFactorialValue(5));
        System.out.println(getDpFactorialValue(5));
        System.out.println(getDpFactorialValue(2));
    }

    public static int getFactorialValue(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException();
        }
        int result = 1;
        for (int i = 1; i <= input; i++) {
            result *= i;
        }
        return result;
    }

    public static int getRecursiveFactorialValue(int input) {
        if (input < 0) {
            throw new IllegalArgumentException();
        }
        if (input == 0 || input == 1) {
            return 1;
        }
        return input * getRecursiveFactorialValue(input - 1);
    }

    public static int getDpFactorialValue(int input) {
        if (input < factorialSubresults.size()) {
            return factorialSubresults.get(input);
        }
        while (factorialSubresults.size() <= input) {
            Integer lastComputedValue = factorialSubresults.getLast();
            factorialSubresults.add(lastComputedValue * factorialSubresults.size());
        }
        return factorialSubresults.get(input);
    }

}
