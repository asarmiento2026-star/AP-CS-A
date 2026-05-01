/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package parkmanager;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author ASarmiento2026
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

public class ParkManager {
    private String parkName = "Jurassic Java Park";
    private int turnCount = 0;
    private double playerBudget = 5000.00;
    private Park myPark;
    private Scanner scanner = new Scanner(System.in);
    private final String SAVE_FILE = "park_data.txt";

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
        boolean isRunning = true;
        System.out.println("Welcome to " + parkName);

        while (isRunning) {
            System.out.println("\n----------------------------");
            System.out.println("Day: " + turnCount + " | Budget: $" + playerBudget);
            System.out.println("1. Next Day | 2. Buy Tiger | 3. Status | 4. Save | 5. Load | 6. Exit");
            System.out.print("Choose: ");

            if (!scanner.hasNextLine()) break; 
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": processDay(); break;
                case "2": buyTiger(); break;
                case "3": displayStatus(); break;
                case "4": saveGame(); break;
                case "5": loadGame(); break;
                case "6": 
                    isRunning = false;
                    System.out.println("Goodbye!");
                    break;
                default: System.out.println("Invalid command.");
            }
        }
    }
    
    public void saveGame() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            writer.println(turnCount);
            writer.println(playerBudget);
            for (Enclosure enc : myPark.getEnclosures()) {
                for (Animal a : enc.getAnimals()) {
                    writer.println(a.name + "," + a.age + "," + a.hungerLevel);
                }
            }
            System.out.println("Progress saved to " + SAVE_FILE);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public void loadGame() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            System.out.println("No save file found.");
            return;
        }

        try (Scanner fileReader = new Scanner(file)) {
            if (fileReader.hasNextLine()) turnCount = Integer.parseInt(fileReader.nextLine());
            if (fileReader.hasNextLine()) playerBudget = Double.parseDouble(fileReader.nextLine());

            myPark.getEnclosures().get(0).getAnimals().clear();

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Tiger loadedTiger = new Tiger(parts[0], Integer.parseInt(parts[1]));
                    loadedTiger.hungerLevel = Integer.parseInt(parts[2]);
                    myPark.getEnclosures().get(0).getAnimals().add(loadedTiger);
                }
            }
            System.out.println("Game Loaded Successfully!");
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public void buyTiger() {
        if (playerBudget >= 1000) {
            System.out.print("What would you like to name your new Tiger? ");
            String name = scanner.nextLine().trim();
            
            // Default name fallback in case the user leaves it blank
            if (name.isEmpty()) {
                name = "Unnamed Tiger";
            }

            playerBudget -= 1000;
            myPark.getEnclosures().get(0).getAnimals().add(new Tiger(name, 1));
            System.out.println("Purchased " + name + " the Tiger!");
        } else {
            System.out.println("Not enough money!");
        }
    }

    public void displayStatus() {
        System.out.println("\n--- Park Inventory ---");
        for (Enclosure enc : myPark.getEnclosures()) {
            System.out.println("Enclosure Animals: " + enc.getAnimals().size());
            for (Animal a : enc.getAnimals()) {
                System.out.println(" - " + a.name + " (Hunger: " + a.hungerLevel + ")");
            }
        }
    }

    public void processDay() {
        turnCount++;
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
    }
}

class Park {
    private ArrayList<Enclosure> enclosures = new ArrayList<>();
    public ArrayList<Enclosure> getEnclosures() { return enclosures; }
}

class Enclosure {
    private ArrayList<Animal> animals = new ArrayList<>();
    public Enclosure(int size) {}
    public ArrayList<Animal> getAnimals() { return animals; }
}

abstract class Animal {
    protected String name;
    protected int age;
    protected int hungerLevel = 0;
    public Animal(String name, int age) { this.name = name; this.age = age; }
    public abstract void eat();
    public void statusUpdate() {
        hungerLevel += 20;
        System.out.println(name + " is hungrier...");
    }
}

class Mammal extends Animal {
    public Mammal(String name, int age) { super(name, age); }
    @Override public void eat() { System.out.println(name + " is eating."); }
}

class Tiger extends Mammal {
    public Tiger(String name, int age) { super(name, age); }
    @Override public void eat() { 
        System.out.println(name + " eats meat."); 
        hungerLevel = 0; 
    }
}

class GuestEvent {
    private String desc;
    private int money;
    public GuestEvent(String desc, int money) { this.desc = desc; this.money = money; }
    public int getFinancialEffect() { return money; }
    public void triggerScenario() { System.out.println("[EVENT] " + desc); }
}