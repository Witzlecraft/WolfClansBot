package wolfscriptbot.services;

import wolfscriptbot.object.Animal;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class AnimalService {

    private ArrayList<Animal> animals = new ArrayList<>();

    public void createRandomAnimals() {
        for (int i = 0; i <= 750; i++) { //Animal count
            int x = new Random().nextInt(100);
            int y = new Random().nextInt(100);

            Animal animal = new Animal();
            animal.setX(x);
            animal.setY(y);
            animal.setId(new Random().nextInt(1000000000));
            animals.add(animal);
        }
    }

    public void createRandomAnimal() {
        int x = new Random().nextInt(100);
        int y = new Random().nextInt(100);

        Animal animal = new Animal();
        animal.setX(x);
        animal.setY(y);
        animal.setId(new Random().nextInt(1000000000));
        animals.add(animal);
    }

    public Animal getAnimalOnPos(int x, int y) {
        for (Animal animal : animals) {
            if(x == animal.getX() && y == animal.getY()) {
                return animal;
            }
        }
        return null;
    }

    public ArrayList<Animal> searchAnimals(int xPos, int yPos) {
        //VTL DISTANCE
        ArrayList<Animal> animalTemp = new ArrayList<>();
        Point point;
        for (Animal animal : animals) {
            /*
            if(xPos == animal.getX() && yPos == animal.getY()) {
                animalTemp.add(animal);
            }
            if(xPos-1 == animal.getX() && yPos == animal.getY()) {
                animalTemp.add(animal);
            }
            if(xPos+1 == animal.getX() && yPos == animal.getY()) {
                animalTemp.add(animal);
            }
            if(xPos == animal.getX() && yPos-1 == animal.getY()) {
                animalTemp.add(animal);
            }
            if(xPos == animal.getX() && yPos+1 == animal.getY()) {
                animalTemp.add(animal);
            }
            if(xPos-1 == animal.getX() && yPos-1 == animal.getY()) {
                animalTemp.add(animal);
            }
            if(xPos-1 == animal.getX() && yPos+1 == animal.getY()) {
                animalTemp.add(animal);
            }
            if(xPos+1 == animal.getX() && yPos-1 == animal.getY()) {
                animalTemp.add(animal);
            }
            if(xPos+1 == animal.getX() && yPos+1 == animal.getY()) {
                animalTemp.add(animal);
            }
            */
            if(Math.abs(animal.getX()-xPos) <= 1 && Math.abs(animal.getY()-yPos) <= 1) {
                animalTemp.add(animal);
            }
        }
        return animalTemp;
    }

}
