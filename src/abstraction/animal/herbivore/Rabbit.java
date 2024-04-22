package abstraction.animal.herbivore;


import abstraction.plant.Plants;
import interfaces.Organism;
import islands.Cell;
import islands.Island;

import java.util.Random;

public class Rabbit extends Herbivores {
    private final double REPRODUCTION_PROBABILITY = 0.3;

    private Island island;
    private final String pictureOfAnimal = "\uD83D\uDC07";
    private final String nameOfAnimal = "Rabbit";
    private final double weightOfAnimal = 2d;
    private final int maxStepsMove = 2;
    private final double kilogramsOfFoodForCompleteSaturation = 0.45;
    private int x;
    private int y;
    private Cell[][] cells;

    public Rabbit(){
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
                if (island.isValidLocation(i, j) && cells[i][j].getResidents().stream().noneMatch(o -> o instanceof Rabbit)) {
                    if (Math.random() < REPRODUCTION_PROBABILITY) {
                        Rabbit newRabbit = new Rabbit();
                        island.moveAnimal(newRabbit, i, j);
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
