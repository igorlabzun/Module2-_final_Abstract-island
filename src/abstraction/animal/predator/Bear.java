package abstraction.animal.predator;

import abstraction.animal.Animal;
import abstraction.animal.herbivore.*;
import islands.Cell;
import islands.Island;

import java.util.HashMap;
import java.util.Random;

public class Bear extends Predators {
    private final double REPRODUCTION_PROBABILITY = 0.3;
    private Island island;
    private final String pictureOfAnimal = "\uD83D\uDC3B";
    private final String nameOfAnimal = "Bear";
    private final double weightOfAnimal = 500d;
    private final int maxStepsMove = 2;
    Random random = new Random();
    private final double kilogramsOfFoodForCompleteSaturation = 80d;
    private final HashMap<Animal, Integer> animalsThatCanBeEaten = new HashMap<>();
    private final int canEatRabbit = 80;
    private final int canEatGoat = 70;
    private final int canEatBoa = 80;
    private final int canEatMouse = 90;
    private final int canEatDeer = 80;
    private final int canEatSheep = 70;
    private final int canEatHorse = 40;
    private int x;
    private int y;
    private Cell[][] cells;

    public Bear() {
        setPictureOfAnimal(pictureOfAnimal);
        setNameOfAnimal(nameOfAnimal);
        setMaxStepsMove(maxStepsMove);
        setKilogramsOfFoodForCompleteSaturation(kilogramsOfFoodForCompleteSaturation);
        setWeightOfAnimal(weightOfAnimal);
    }

    {
        animalsThatCanBeEaten.put(new Rabbit(), canEatRabbit);
        animalsThatCanBeEaten.put(new Goat(), canEatGoat);
        animalsThatCanBeEaten.put(new Boa(), canEatBoa);
        animalsThatCanBeEaten.put(new Mouse(), canEatMouse);
        animalsThatCanBeEaten.put(new Deer(), canEatDeer);
        animalsThatCanBeEaten.put(new Sheep(), canEatSheep);
        animalsThatCanBeEaten.put(new Horse(), canEatHorse);
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
                if (island.isValidLocation(i, j) && cells[i][j].getResidents().stream().noneMatch(o -> o instanceof Bear)) {
                    if (Math.random() < REPRODUCTION_PROBABILITY) {
                        Bear newBear = new Bear();
                        island.moveAnimal(newBear, i, j);
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
