package org.example;

import java.security.SecureRandom;
import java.util.Random;

public class Main {

//    private final int magic_number = 5;

    public void doSomething() {
        Random random = new Random();
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.getAlgorithm();
        if (5 > 10)
            System.out.println("Magic number is greater than 10");

        for(int i = 0; i < 7; i++) {
            System.out.println("Loop index: " + i);
        }
    }

    public void BadMethodName(){
        System.out.println("Bad method name.");
    }
}