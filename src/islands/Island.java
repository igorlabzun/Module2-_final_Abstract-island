package islands;




import abstraction.animal.Animal;
import abstraction.animal.herbivore.*;
import abstraction.animal.predator.*;
import abstraction.plant.Plants;
import interfaces.Organism;

import java.util.*;



public class Island implements Runnable {
    private final int width;
    private final int height;
    private final Cell[][] cells;
    public final Random random;
    public final List<Organism> herbivores;
    public final List<Organism> predators;




    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];
        this.random = new Random();
        this.herbivores = new ArrayList<>();
        this.predators = new ArrayList<>();
        initializeCells();
    }

    private void initializeCells() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Organism organism = getRandomAnimal();
                Set<Organism> residents = new HashSet<>();
                residents.add(organism);
                cells[i][j] = new Cell(residents);
            }
        }
    }
    public Organism getRandomAnimal(){
        double randomOrganism = Math.random();
        if (randomOrganism<0.3){
            return new Plants();
        }else if (randomOrganism <0.75){
            return getRandomHerbivores();
        }else {
            return getRandomPredators();
        }

    }

    public  Herbivores getRandomHerbivores(){
        double itValue = Math.random();
        if (itValue < 0.1){
            return new Rabbit();
        } else if (itValue < 0.2) {
            return new Buffalo();
        }else if (itValue < 0.3) {
            return new Sheep();
        }else if (itValue < 0.4) {
            return new Goat();
        }else if (itValue < 0.5) {
            return new Boar();
        }else if (itValue < 0.6) {
            return new Duck();
        }else if (itValue < 0.7) {
            return new Caterpillar();
        }else if (itValue < 0.8) {
            return new Mouse();

        }else if (itValue < 0.9) {
            return new Deer();
        }else {
        return new Horse();
        }
    }
    public  Predators getRandomPredators(){
        double randomValue = Math.random();
        if (randomValue < 0.2){
            return new Bear();
        } else if (randomValue < 0.4) {
            return new Boa();
        }else if (randomValue < 0.6) {
            return new Fox();
        }else if (randomValue < 0.8) {
            return new Wolf();}
         else {
            return new Eagle();
        }
    }

    public void moveAnimal(Animal animal, int newX, int newY) {
        if (isValidLocation(newX, newY)) {
            cells[animal.getX()][animal.getY()].removeAnimal(animal);
            animal.setX(newX);
            animal.setY(newY);
            cells[newX][newY].addAnimal(animal);
        }
    }
    public synchronized void simulateDay() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Set<Organism> residents;
                synchronized (cells[i][j]) {
                    residents = new HashSet<>(cells[i][j].getResidents());
                }
                for(Organism organism : residents){
                    if(organism instanceof Animal){
                        residents.add(organism);
                    }else if(organism instanceof Plants){
                        residents.add(new Plants());
                    }
                }

            }

        }
        printIsland();
        printStatistics();

    }




    public void printStatistics() {
        int herbivoreCount = 0;
        int predatorCount = 0;
        int plantCount = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (Organism organism : cells[i][j].getResidents()) {
                    if (organism instanceof Herbivores) {
                        herbivoreCount++;
                    } else if (organism instanceof Predators) {
                        predatorCount++;
                    } else if (organism instanceof Plants) {
                        plantCount++;
                    }
                }
            }
        }

        System.out.println("Island life statistics:");
        System.out.println("Herbivores: " + herbivoreCount);
        System.out.println("Predators: " + predatorCount);
        System.out.println("Plants: " + plantCount);
    }

    private void printIsland() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = cells[i][j];
                String symbol = String.valueOf(getSymbolForCell(cell));
                System.out.print(symbol + " ");

            }
            System.out.println();
        }
        System.out.println();

    }


    private String getSymbolForCell(Cell cell) {
        for (Organism organism : cell.getResidents()) {
            if (organism instanceof Rabbit) {
                return ((Rabbit) organism).getPictureOfAnimal();
            } else if (organism instanceof Buffalo) {
                return ((Buffalo) organism).getPictureOfAnimal();
            } else if (organism instanceof Horse) {
                return ((Horse) organism).getPictureOfAnimal();
            } else if (organism instanceof Mouse) {
                return ((Mouse) organism).getPictureOfAnimal();
            } else if (organism instanceof Deer) {
                return ((Deer) organism).getPictureOfAnimal();
            } else if (organism instanceof Duck) {
                return ((Duck) organism).getPictureOfAnimal();
            } else if (organism instanceof Boar) {
                return ((Boar) organism).getPictureOfAnimal();
            } else if (organism instanceof Sheep) {
                return ((Sheep) organism).getPictureOfAnimal();
            } else if (organism instanceof Wolf) {
                return ((Wolf) organism).getPictureOfAnimal();
            } else if (organism instanceof Bear) {
                return ((Bear) organism).getPictureOfAnimal();
            } else if (organism instanceof Fox) {
                return ((Fox) organism).getPictureOfAnimal();
            } else if (organism instanceof Boa) {
                return ((Boa) organism).getPictureOfAnimal();
            } else if (organism instanceof Eagle) {
                return ((Eagle) organism).getPictureOfAnimal();
            }
            else if ( organism instanceof Plants) {
                return "\uD83C\uDF3F";
            }
        }
        return " ";
    }



    public boolean isValidLocation(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    @Override
    public void run() {
        simulateDay();
    }
}
