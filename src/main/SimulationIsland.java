package main;

import abstraction.animal.herbivore.Herbivores;
import abstraction.animal.predator.Predators;
import islands.Island;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationIsland implements Runnable {
    int numberThread = 15;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();


    public void init() {
        executorService.execute(new Herbivores());
        executorService.execute(new Predators());
        executorService.execute(new Island(10, 10));
    }


    @Override
    public void run() {
        try {
            for (int i = 0; i < numberThread; i++) {
                init();
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }
}
