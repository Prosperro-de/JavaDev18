package org.example;

public class ReverseService {

    public String reverse(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String[] splittedInput = input.split("\\s+");
        StringBuilder reversed = new StringBuilder();
        for (String word : splittedInput) {
            reversed.append(getReversedWord(word)).append(" ");
        }
        return reversed.toString().strip();
    }

    private StringBuilder getReversedWord(String word) {
        return new StringBuilder(word).reverse();
    }
}
