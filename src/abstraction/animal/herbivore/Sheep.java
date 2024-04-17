package abstraction.animal.herbivore;

import abstraction.plant.Plants;
import interfaces.Organism;
import islands.Cell;
import islands.Island;

import java.util.Random;

public class Sheep extends Herbivores {
    private final double REPRODUCTION_PROBABILITY = 0.3;
    private Island island;
    private final String pictureOfAnimal = "\uD83D\uDC11";
    private final String nameOfAnimal = "Sheep";
    private final double weightOfAnimal = 70d;
    private final int maxStepsMove = 3;
    private final double kilogramsOfFoodForCompleteSaturation = 15d;
    private int x;
    private int y;
    private Cell[][] cells;
    public Sheep(){
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
                if (island.isValidLocation(i, j) && cells[i][j].getResidents().stream().noneMatch(o -> o instanceof Sheep)) {
                    if (Math.random() < REPRODUCTION_PROBABILITY) {
                        Sheep newSheep = new Sheep();
                        island.moveAnimal(newSheep, i, j);
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
