package org.example;

import java.util.stream.Stream;

public class Main {
public static void main(String[] args) {
    System.out.println("Hello, World!");
}

public void extraSpaceMethod() {

    System.out.println("    .  Extra space here.    ");
}

public void iterate() {
    Stream.iterate(0, i -> i + 1)
        .limit(10)
        .map(i -> i * i)
        .distinct()
        .mapToInt(i -> i)
        .forEach(System.out::println);
}
}
