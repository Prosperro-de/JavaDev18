package org.example.interface_segregation.good;

public class Robot implements Workable {
    @Override
    public void work() {
        System.out.println("Robot is working");
    }
}
