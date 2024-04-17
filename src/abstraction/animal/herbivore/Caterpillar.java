package abstraction.animal.herbivore;

import abstraction.plant.Plants;
import islands.Cell;
import islands.Island;

public class Caterpillar extends Herbivores {
    private final double REPRODUCTION_PROBABILITY = 0.3;
    private Island island;

    private final String pictureOfAnimal = "\uD83D\uDC1B";
    private final String nameOfAnimal = "Caterpillar";
    private final double weightOfAnimal = 0.01;
    private final int maxStepsMove = 0;
    private final double kilogramsOfFoodForCompleteSaturation = 0.0;
    private int x;
    private int y;
    private Cell[][] cells;
    public Caterpillar(){
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

        super.move();
    }

    @Override
    public void reproduce() {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (island.isValidLocation(i, j) && cells[i][j].getResidents().stream().noneMatch(o -> o instanceof Caterpillar)) {
                    if (Math.random() < REPRODUCTION_PROBABILITY) {
                        Caterpillar newCaterpillar = new Caterpillar();
                        island.moveAnimal(newCaterpillar, i, j);
                        return;
                    }
                }
            }
        }
    }



    @Override
    public void run() {
        eat();
        reproduce();
    }
}
