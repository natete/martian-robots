package com.nachogonzalezbullon;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        final Simulator simulator = new Simulator(args[0]);
        simulator.simulate();
    }
}
