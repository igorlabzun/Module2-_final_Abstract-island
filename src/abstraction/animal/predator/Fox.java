package abstraction.animal.predator;

import abstraction.animal.Animal;
import abstraction.animal.herbivore.*;
import islands.Cell;
import islands.Island;

import java.util.HashMap;
import java.util.Random;

public class Fox extends Predators {
    private final double REPRODUCTION_PROBABILITY = 0.3;
    private Island island;
    private final String pictureOfAnimal = "\uD83E\uDD8A";
    private final String nameOfAnimal = "Fox";
    private final double weightOfAnimal = 8d;
    private final int maxStepsMove = 2;
    private final double kilogramsOfFoodForCompleteSaturation = 2d;
    Random random = new Random();
    private final HashMap<Animal,Integer> animalsThatCanBeEaten = new HashMap<>();
    private final int canEatRabbit = 70;

    private final int canEatMouse = 90;
    private int x;
    private int y;
    private Cell[][] cells;
    public Fox(){
        setPictureOfAnimal(pictureOfAnimal);
        setNameOfAnimal(nameOfAnimal);
        setMaxStepsMove(maxStepsMove);
        setKilogramsOfFoodForCompleteSaturation(kilogramsOfFoodForCompleteSaturation);
        setWeightOfAnimal(weightOfAnimal);
    }

    @Override
    public void eat() {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (island.isValidLocation(i,j) && cells[i][j].getResidents().stream().anyMatch(o -> o instanceof Herbivores)) {
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
                if (island.isValidLocation(i, j) && cells[i][j].getResidents().stream().noneMatch(o -> o instanceof Fox)) {
                    if (Math.random() < REPRODUCTION_PROBABILITY) {
                        Fox newFox = new Fox();
                        island.moveAnimal(newFox, i, j);
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
