package abstraction.animal;

import interfaces.Organism;
import islands.Island;

import java.util.Random;

public abstract class Animal implements Organism, Runnable {
    private int x;
    private int y;
    private volatile String pictureOfAnimal;
    private String nameOfAnimal;
    private double weightOfAnimal;
    private volatile double kilogramsOfFoodForCompleteSaturation;
    private int maxStepsMove;

    public abstract void eat();
    public abstract void move();
    public abstract void reproduce();


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }




    public int getMaxStepsMove() {
        return maxStepsMove;
    }

    public void setMaxStepsMove(int maxStepsMove) {
        this.maxStepsMove = maxStepsMove;
    }

    public String getPictureOfAnimal() {
        return pictureOfAnimal;
    }

    public void setPictureOfAnimal(String pictureOfAnimal) {
        this.pictureOfAnimal = pictureOfAnimal;
    }

    public String getNameOfAnimal() {
        return nameOfAnimal;
    }

    public void setNameOfAnimal(String nameOfAnimal) {
        this.nameOfAnimal = nameOfAnimal;
    }

    public double getWeightOfAnimal() {
        return weightOfAnimal;
    }

    public void setWeightOfAnimal(double weightOfAnimal) {
        this.weightOfAnimal = weightOfAnimal;
    }

    public double getKilogramsOfFoodForCompleteSaturation() {
        return kilogramsOfFoodForCompleteSaturation;
    }

    public void setKilogramsOfFoodForCompleteSaturation(double kilogramsOfFoodForCompleteSaturation) {
        this.kilogramsOfFoodForCompleteSaturation = kilogramsOfFoodForCompleteSaturation;
    }

    @Override
    public void run() {

    }
}











