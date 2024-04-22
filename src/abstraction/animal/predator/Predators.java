package abstraction.animal.predator;

import abstraction.animal.Animal;
import java.util.ArrayList;
import java.util.List;

public class Predators extends Animal implements Runnable {
    private List<Predators> predators;
    public List<Predators> getPredators() {
        predators = new ArrayList<>();
        predators.add(new Wolf());
        predators.add(new Bear());
        predators.add(new Fox());
        predators.add(new Eagle());
        predators.add(new Boa());
        return predators;
    }


    @Override
    public void eat() {

    }

    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }

    @Override
    public void run() {

    }
}
