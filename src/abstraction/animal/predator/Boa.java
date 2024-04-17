package abstraction.animal.predator;

import abstraction.animal.Animal;
import abstraction.animal.herbivore.Herbivores;
import abstraction.animal.herbivore.Mouse;
import abstraction.animal.herbivore.Rabbit;
import islands.Cell;
import islands.Island;

import java.util.HashMap;
import java.util.Random;

public class Boa extends Predators {
    private final double REPRODUCTION_PROBABILITY = 0.3;
    private Island island;
    private final String pictureOfAnimal = "\uD83D\uDC0D";
    private final String nameOfAnimal = "Boa";
    private final double weightOfAnimal = 15d;
    private final int maxStepsMove = 1;
    Random random = new Random();
    private final double kilogramsOfFoodForCompleteSaturation = 3d;
    private final HashMap<Animal,Integer> animalsThatCanBeEaten = new HashMap<>();
    private final int canEatRabbit = 20;
    private final int canEatMouse = 40;
    private final int canEatFox = 40;
    private int x;
    private int y;
    private Cell[][] cells;
    public Boa(){
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
                if (island.isValidLocation(i, j) && cells[i][j].getResidents().stream().noneMatch(o -> o instanceof Boa)) {
                    if (Math.random() < REPRODUCTION_PROBABILITY) {
                        Boa newBoa = new Boa();
                        island.moveAnimal(newBoa, i, j);
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
