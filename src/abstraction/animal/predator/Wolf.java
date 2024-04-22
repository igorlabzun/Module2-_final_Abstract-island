package abstraction.animal.predator;

import abstraction.animal.Animal;
import abstraction.animal.herbivore.*;
import islands.Cell;
import islands.Island;

import java.util.HashMap;
import java.util.Random;

public class Wolf extends Predators {
    private final double REPRODUCTION_PROBABILITY = 0.3;
    private Island island;
    private final String pictureOfAnimal = "\uD83D\uDC3A";
    private final String nameOfAnimal = "Wolf";
    private final double weightOfAnimal = 50d;
    private final int maxStepsMove = 3;
    private final double kilogramsOfFoodForCompleteSaturation = 8d;
    Random random = new Random();
    private final HashMap<Animal, Integer> animalsThatCanBeEaten = new HashMap<>();
    private final int canEatRabbit = 60;
    private final int canEatGoat = 60;
    private final int canEatHorse = 10;
    private final int canEatMouse = 80;
    private final int canEatDeer = 15;
    private final int canEatSheep = 70;
    private int x;
    private int y;
    private Cell[][] cells;

    public Wolf() {
        setPictureOfAnimal(pictureOfAnimal);
        setNameOfAnimal(nameOfAnimal);
        setMaxStepsMove(maxStepsMove);
        setKilogramsOfFoodForCompleteSaturation(kilogramsOfFoodForCompleteSaturation);
        setWeightOfAnimal(weightOfAnimal);
    }

    {
        animalsThatCanBeEaten.put(new Rabbit(), canEatRabbit);
        animalsThatCanBeEaten.put(new Goat(), canEatGoat);
        animalsThatCanBeEaten.put(new Horse(), canEatHorse);
        animalsThatCanBeEaten.put(new Mouse(), canEatMouse);
        animalsThatCanBeEaten.put(new Deer(), canEatDeer);
        animalsThatCanBeEaten.put(new Sheep(), canEatSheep);
    }

    @Override
    public void eat() {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (island.isValidLocation(i, j) && cells[i][j].getResidents().stream().anyMatch(o -> o instanceof Herbivores)) {
                    cells[i][j].getResidents().removeIf(o -> o instanceof Herbivores);
                    return;
                }
            }
        }
    }

    @Override
    public void move() {
        int newX = x + getRandomMovement();
        int newY = y + getRandomMovement();
        if (island.isValidLocation(newX, newY) && cells[newX][newY].getResidents().isEmpty()) {
            island.moveAnimal(this, newX, newY);
            x = newX;
            y = newY;
        }
    }

    protected int getRandomMovement() {
        return random.nextInt(maxStepsMove) - 1;
    }

    @Override
    public void reproduce() {

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (island.isValidLocation(i, j) && cells[i][j].getResidents().stream().noneMatch(o -> o instanceof Wolf)) {
                    if (Math.random() < REPRODUCTION_PROBABILITY) {
                        Wolf newWolf = new Wolf();
                        island.moveAnimal(newWolf, i, j);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        move();
        eat();
        reproduce();
    }
}
