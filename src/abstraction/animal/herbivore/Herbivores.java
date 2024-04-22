package abstraction.animal.herbivore;

import abstraction.animal.Animal;


import java.util.ArrayList;
import java.util.List;


public  class Herbivores extends Animal implements Runnable {
    private final List<Herbivores> herbivores = new ArrayList<>();;
    public List<Herbivores> getHerbivores() {
        herbivores.add(new Rabbit());
        herbivores.add(new Buffalo());
        herbivores.add(new Horse());
        herbivores.add(new Deer());
        herbivores.add(new Mouse());
        herbivores.add(new Duck());
        herbivores.add(new Boar());
        herbivores.add(new Sheep());
        herbivores.add(new Goat());
        herbivores.add(new Caterpillar());
        return herbivores;
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
