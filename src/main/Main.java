package main;


public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new SimulationIsland());
        thread.start();

    }
}
