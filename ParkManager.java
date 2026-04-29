/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package parkmanager;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ASarmiento2026
 */

public class ParkManager {
    private String parkName = "Park";
    private int turnCount = 0;
    private double playerBudget = 5000.00;
    private Park myPark;

    public ParkManager() {
        this.myPark = new Park();
        Enclosure felineDen = new Enclosure(10);
        felineDen.getAnimals().add(new Tiger("Bob", 5));
        myPark.getEnclosures().add(felineDen);
    }

    public static void main(String[] args) {
    ParkManager game = new ParkManager();
    game.runGame();
    }

    public void runGame() {
        System.out.println("Welcome to " + parkName);
        for (int i = 0; i < 3; i++) {
        processDay();
        }
    }

    public void processDay() {
        turnCount++;
        System.out.println("\n--- Day " + turnCount + " ---");

        for (Enclosure enc : myPark.getEnclosures()) {
            for (Animal a : enc.getAnimals()) {
                a.statusUpdate();
                a.eat();

                playerBudget -= 50.0;
            }
        }

        if (new Random().nextBoolean()) {
            GuestEvent visit = new GuestEvent("School Trip", 200);
            visit.triggerScenario();
            this.playerBudget += visit.getFinancialEffect();
        }

        System.out.println("Current Budget: $" + playerBudget);
    }
}

class Park {
    private ArrayList<Enclosure> enclosures = new ArrayList<>();
    private double cashAmount = 1000.0;

    public ArrayList<Enclosure> getEnclosures() { return enclosures; }
}

class Enclosure {
    private ArrayList<Animal> animals = new ArrayList<>();
    private int enclosureSize;

    public Enclosure(int size) { this.enclosureSize = size; }
    public ArrayList<Animal> getAnimals() { return animals; }
}

abstract class Animal {
    protected String name;
    protected int age;
    protected int hungerLevel = 0;
    protected String habitatType;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract void eat();

    public void statusUpdate() {
        hungerLevel += 20;
        System.out.println(name + " is getting older and hungrier...");
    }
}

class Mammal extends Animal {
    protected int gestationPeriod = 90;
    public Mammal(String name, int age) { super(name, age); }
    @Override public void eat() { System.out.println(name + " is nursing or foraging."); }
}

class Tiger extends Mammal {
    public Tiger(String name, int age) { super(name, age); }
    public void carnivore() { System.out.println(name + " eats raw meat."); }

    @Override
    public void eat() { carnivore(); hungerLevel = 0; }
}

class Reptile extends Animal {
    public Reptile(String name, int age) { super(name, age); }
    public void layEgg() { System.out.println(name + " laid an egg."); }
    @Override public void eat() { System.out.println(name + " eats insects."); }
}

abstract class GameEvent {
    public abstract void triggerScenario();
}

class GuestEvent extends GameEvent {
    private String guestDescription;
    private int financialEffect;

    public GuestEvent(String desc, int money) {
        this.guestDescription = desc;
        this.financialEffect = money;
    }

    public int getFinancialEffect() { return financialEffect; }

    @Override
    public void triggerScenario() {
        System.out.println("[EVENT] " + guestDescription + "! Earned: $" + financialEffect);
    }
}