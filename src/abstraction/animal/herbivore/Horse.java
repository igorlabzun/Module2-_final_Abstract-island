package abstraction.animal.herbivore;

import abstraction.plant.Plants;

import islands.Cell;
import islands.Island;

import java.util.Random;

public class Horse extends Herbivores {
    private final double REPRODUCTION_PROBABILITY = 0.3;
    private Island island;
    private final String pictureOfAnimal = "\uD83D\uDC0E";
    private final String nameOfAnimal = "Horse";
    private final double weightOfAnimal = 400d;
    private final int maxStepsMove = 4;
    private final double kilogramsOfFoodForCompleteSaturation = 60d;
    private int x;
    private int y;
    private Cell[][] cells;
    public Horse(){
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
                if (island.isValidLocation(i,j) && cells[i][j].getResidents().stream().anyMatch(o -> o instanceof Plants)) {
                    cells[i][j].getResidents().removeIf(o -> o instanceof Plants);
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
        Random random = new Random();
        return random.nextInt(maxStepsMove) - 1;
    }
    @Override
    public void reproduce() {

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (island.isValidLocation(i, j) && cells[i][j].getResidents().stream().noneMatch(o -> o instanceof Horse)) {
                    if (Math.random() < REPRODUCTION_PROBABILITY) {
                        Horse newHorse = new Horse();
                        island.moveAnimal(newHorse, i, j);
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
