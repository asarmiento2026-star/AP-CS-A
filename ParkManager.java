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

//create ParkManager class for game loop and game state
public class ParkManager {
    private String parkName = "San Diego Safari Park";
    
    private int turnCount = 0;
    private double playerBudget = 5000.00;
    private Park myPark;
    private Scanner scanner = new Scanner(System.in);
    
    //all file paths
    private final String SAVE_FILE = "park_data.txt";
    private final String LOG_FILE = "daily_log.txt";
    private final String RECORD_FILE = "high_score.txt";

    //initialize park with an enclosure with bob
    public ParkManager() {
        this.myPark = new Park();
        Enclosure mainEnclosure = new Enclosure(10);
        mainEnclosure.getAnimals().add(new Tiger("Bob", 5));
        myPark.getEnclosures().add(mainEnclosure);
    }

    public static void main(String[] args) {
        ParkManager game = new ParkManager();
        game.runGame();
    }

    //process user choices and display menu
    public void runGame() {
        boolean isRunning = true;
        System.out.println("Welcome to " + parkName);
        System.out.println("Manage your park by buying animals and caring for them.");
        System.out.println("Follow the prompts (numbers 1-4) to try and get the most profit to win.");
        System.out.println("Animals will get hungry over time, so be careful!");
        System.out.println("You can save your progress (number 5) or load any progress saved (number 6).");
        System.out.println("To exit press and enter the number 7.");

        while (isRunning) {
            //check if park is bankrupt
            if (playerBudget < 0) {
                System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("GAME OVER: Your park has gone bankrupt!");
                System.out.println("Final Turn Count: " + turnCount);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                writeToLog("GAME OVER: Bankruptcy reached.");
                break; 
            }

            System.out.println("\n----------------------------");
            System.out.println("Day: " + turnCount + " | Budget: $" + String.format("%.2f", playerBudget));
            System.out.println("1. Next Day | 2. Buy Animal | 3. Feed Animals | 4. Status | 5. Save | 6. Load | 7. Exit");
            System.out.print("Choose: ");

            if (!scanner.hasNextLine()) break; 
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": processDay(); break;
                case "2": buyAnimal(); break;
                case "3": feedAnimals(); break; 
                case "4": displayStatus(); break;
                case "5": saveGame(); break;
                case "6": loadGame(); break;
                case "7": 
                    isRunning = false;
                    System.out.println("Goodbye!");
                    break;
                default: System.out.println("Invalid command.");
            }
        }
    }

    //files to track park history, high score
    public void writeToLog(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println("Day " + turnCount + ": " + message);
        } catch (IOException e) {
            System.out.println("Log error.");
        }
    }

    public void updateHighScore() {
        double currentTopScore = 0;
        File file = new File(RECORD_FILE);
        if (file.exists()) {
            try (Scanner reader = new Scanner(file)) {
                if (reader.hasNextDouble()) currentTopScore = reader.nextDouble();
            } catch (FileNotFoundException e) { }
        }
        if (playerBudget > currentTopScore) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(RECORD_FILE))) {
                writer.println(playerBudget);
                System.out.println("NEW RECORD: $" + String.format("%.2f", playerBudget));
            } catch (IOException e) { }
        }
    }

    //reset hunger of animals in park
    public void feedAnimals() {
        double feedCost = 0;
        int count = 0;
        
        for (Enclosure enc : myPark.getEnclosures()) {
            for (Animal a : enc.getAnimals()) {
                feedCost += 20.0;
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No animals to feed!");
            return;
        }
        //deduct money from budget and call feed method
        if (playerBudget >= feedCost) {
            playerBudget -= feedCost;
            writeToLog("Fed " + count + " animals for $" + feedCost);
            for (Enclosure enc : myPark.getEnclosures()) {
                for (Animal a : enc.getAnimals()) a.eat();
            }
        } else {
            System.out.println("Not enough money to feed animals!");
        }
    }

    //advance game one day and increase hunger/events + maintenance costs
    public void processDay() {
        turnCount++;
        for (Enclosure enc : myPark.getEnclosures()) {
            for (Animal a : enc.getAnimals()) {
                a.statusUpdate();
                playerBudget -= 35.0;
            }
        }
        if (new Random().nextBoolean()) {
            GuestEvent visit = new GuestEvent("Tourists visited!", 400);
            visit.triggerScenario();
            this.playerBudget += visit.getFinancialEffect();
        }
        writeToLog("Day processed. Budget: " + playerBudget);
        updateHighScore();
    }

    //menu to purchase animals for park
    public void buyAnimal() {
        System.out.println("\n--- Market ---");
        System.out.println("1. Tiger ($1000) | 2. Elephant ($2500) | 3. Komodo Dragon ($1500)");
        System.out.println("4. Monitor Lizard ($800) | 5. Secretary Bird ($1200) | 6. Toucan ($500)");
        System.out.print("Select: ");
        
        String choice = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Unnamed";

        double cost = 0;
        Animal newAnimal = null;

        switch (choice) {
            case "1": cost = 1000; newAnimal = new Tiger(name, 1); break;
            case "2": cost = 2500; newAnimal = new Elephant(name, 1); break;
            case "3": cost = 1500; newAnimal = new KomodoDragon(name, 1); break;
            case "4": cost = 800;  newAnimal = new MonitorLizard(name, 1); break;
            case "5": cost = 1200; newAnimal = new SecretaryBird(name, 1); break;
            case "6": cost = 500;  newAnimal = new Toucan(name, 1); break;
            default: return;
        }

        if (playerBudget >= cost) {
            playerBudget -= cost;
            myPark.getEnclosures().get(0).getAnimals().add(newAnimal);
            writeToLog("Bought " + newAnimal.getClass().getSimpleName() + " named " + name);
            System.out.println("Purchased!");
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    //print all animals and hunger levels
    public void displayStatus() {
        System.out.println("\n--- Park Status ---");
        for (Enclosure enc : myPark.getEnclosures()) {
            for (Animal a : enc.getAnimals()) {
                System.out.println(" - [" + a.getClass().getSimpleName() + "] " + a.name + " | Hunger: " + a.hungerLevel);
            }
        }
    }

    //transfer game data to text files
    public void saveGame() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            writer.println(turnCount);
            writer.println(playerBudget);
            for (Enclosure enc : myPark.getEnclosures()) {
                for (Animal a : enc.getAnimals()) {
                    writer.println(a.getClass().getSimpleName() + "," + a.name + "," + a.age + "," + a.hungerLevel);
                }
            }
            System.out.println("Saved.");
        } catch (IOException e) { }
    }

    //transfer game data from text files
    public void loadGame() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) return;
        try (Scanner fr = new Scanner(file)) {
            turnCount = Integer.parseInt(fr.nextLine());
            playerBudget = Double.parseDouble(fr.nextLine());
            myPark.getEnclosures().get(0).getAnimals().clear();
            while (fr.hasNextLine()) {
                String[] p = fr.nextLine().split(",");
                Animal a = createAnimalByType(p[0], p[1], Integer.parseInt(p[2]));
                if (a != null) {
                    a.hungerLevel = Integer.parseInt(p[3]);
                    myPark.getEnclosures().get(0).getAnimals().add(a);
                }
            }
            System.out.println("Loaded.");
        } catch (Exception e) { }
    }

    private Animal createAnimalByType(String t, String n, int a) {
        switch (t) {
            case "Tiger": return new Tiger(n, a);
            case "Elephant": return new Elephant(n, a);
            case "KomodoDragon": return new KomodoDragon(n, a);
            case "MonitorLizard": return new MonitorLizard(n, a);
            case "SecretaryBird": return new SecretaryBird(n, a);
            case "Toucan": return new Toucan(n, a);
            default: return null;
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

//base class for all animals with common attributes
abstract class Animal {
    protected String name;
    protected int age;
    protected int hungerLevel = 0;
    public Animal(String name, int age) { this.name = name; this.age = age; }
    public abstract void eat();
    public void statusUpdate() {
        hungerLevel += 20;
        if (hungerLevel > 100) hungerLevel = 100;
    }
}

//the categories of animals the park buys
class Mammal extends Animal {
    public Mammal(String name, int age) { super(name, age); }
    @Override public void eat() { hungerLevel = 0; }
}

class Reptile extends Animal {
    public Reptile(String name, int age) { super(name, age); }
    @Override public void eat() { hungerLevel = 0; }
}

class Bird extends Animal {
    public Bird(String name, int age) { super(name, age); }
    @Override public void eat() { hungerLevel = 0; }
}

//the different species of animals
class Tiger extends Mammal {
    public Tiger(String n, int a) { super(n, a); }
    @Override public void eat() { super.eat(); System.out.println(name + " ate meat."); }
}

class Elephant extends Mammal {
    public Elephant(String n, int a) { super(n, a); }
    @Override public void eat() { super.eat(); System.out.println(name + " ate hay."); }
}

class KomodoDragon extends Reptile {
    public KomodoDragon(String n, int a) { super(n, a); }
    @Override public void eat() { super.eat(); System.out.println(name + " ate protein."); }
}

class MonitorLizard extends Reptile {
    public MonitorLizard(String n, int a) { super(n, a); }
    @Override public void eat() { super.eat(); System.out.println(name + " ate eggs."); }
}

class SecretaryBird extends Bird {
    public SecretaryBird(String n, int a) { super(n, a); }
    @Override public void eat() { super.eat(); System.out.println(name + " stomped then ate."); }
}

class Toucan extends Bird {
    public Toucan(String n, int a) { super(n, a); }
    @Override public void eat() { super.eat(); System.out.println(name + " ate fruit."); }
}

//guest events that occur at random (give money to player)
class GuestEvent {
    private String desc;
    private int money;
    public GuestEvent(String d, int m) { this.desc = d; this.money = m; }
    public int getFinancialEffect() { return money; }
    public void triggerScenario() { System.out.println("[EVENT] " + desc); }
}
