package org.example.liskov_subs.bad;

public class Penguin extends Bird { //is a
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins can't fly");
    }
}
