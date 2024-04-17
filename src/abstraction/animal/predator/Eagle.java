package abstraction.animal.predator;

import abstraction.animal.Animal;
import abstraction.animal.herbivore.*;
import islands.Cell;
import islands.Island;

import java.util.HashMap;
import java.util.Random;

public class Eagle extends Predators {
    private final double REPRODUCTION_PROBABILITY = 0.3;
    private Island island;
    private final String pictureOfAnimal = "\uD83E\uDD85";
    private final String nameOfAnimal = "Eagle";
    private final double weightOfAnimal = 6d;
    private final int maxStepsMove = 3;
    Random random = new Random();
    private final double kilogramsOfFoodForCompleteSaturation = 1d;
    private final HashMap<Animal, Integer> animalsThatCanBeEaten = new HashMap<>();
    private final int canEatRabbit = 90;
    private final int canEatMouse = 90;
    private int x;
    private int y;
    private Cell[][] cells;

    public Eagle() {
        setPictureOfAnimal(pictureOfAnimal);
        setNameOfAnimal(nameOfAnimal);
        setMaxStepsMove(maxStepsMove);
        setKilogramsOfFoodForCompleteSaturation(kilogramsOfFoodForCompleteSaturation);
        setWeightOfAnimal(weightOfAnimal);
    }

    {
        animalsThatCanBeEaten.put(new Rabbit(), canEatRabbit);
        animalsThatCanBeEaten.put(new Mouse(), canEatMouse);
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
                if (island.isValidLocation(i, j) && cells[i][j].getResidents().stream().noneMatch(o -> o instanceof Eagle)) {
                    if (Math.random() < REPRODUCTION_PROBABILITY) {
                        Eagle newEagle = new Eagle();
                        island.moveAnimal(newEagle, i, j);
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
