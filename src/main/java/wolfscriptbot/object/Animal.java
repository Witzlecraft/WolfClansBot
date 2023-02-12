package wolfscriptbot.object;

import java.util.ArrayList;

public class Animal {

    public Animal(AnimalType animalType) {
        setAnimalType(animalType);
    }
    public Animal() {
    }

    private int Health = 100;
    private ArrayList<Wolf> hitters = new ArrayList<>();
    private int food = 100;
    private boolean dead;
    private int id;
    private int x, y;
    private AnimalType animalType = AnimalType.DEER;


    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public ArrayList<Wolf> getHitters() {
        return hitters;
    }

    public void setHitters(ArrayList<Wolf> hitters) {
        this.hitters = hitters;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public enum AnimalType {
        DEER
    }
}


