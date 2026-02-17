/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.vehicleprogram;

/**
 *
 * @author sarmi
 */

public class VehicleProgram {
    public static void main(String[] args) {

        //create and print object of sportscar
        SportsCar ferrari = new SportsCar("Ferrari", 200, 2, 300, 15, 211, 660);
        System.out.println(ferrari);

        //create and print object of familysuv
        FamilySUV toyota = new FamilySUV("Toyota", 120, 7, 800, 25, 5000);
        System.out.println(toyota);

        //create and print object of sailboat
        SailBoat sail = new SailBoat("Beneteau", 40);
        System.out.println(sail);

        //create and print object of speedboat
        MotorBoat speedBoat = new MotorBoat("Yamaha", 85, 1000, 300, 120);
        System.out.println(speedBoat);

        //create and print object of passengerjet
        PassengerJet boeing = new PassengerJet("Boeing", 180, 40000);
        System.out.println(boeing);

        //create and print object of fighterjet
        FighterJet f16 = new FighterJet("Lockheed Martin", 1, 20000);
        System.out.println(f16);

        //create and print object of sportmotorcycle
        SportMotorcycle ninja = new SportMotorcycle("Kawasaki", 180, 998, 186);
        System.out.println(ninja);

        //create and print object of cruisermotorcycle
        CruiserMotorcycle harley = new CruiserMotorcycle("Harley-Davidson", 110, 1746);
        System.out.println(harley);
    }
}

//implement speed method to all vehicles
interface Speedometer {
    //set speed method
    public void setSpeed(double inSpeed);
    //get speed method
    public double getSpeed();
}

//create vehicle parent class
class Vehicle implements Speedometer {

    //use protected variables for subclass access- brand, speed, passengers, weight
    protected String brandName = "";
    protected double speed = 0.0;
    protected int passengers = 0;
    protected double cargoWeight = 0.0;

    //create default constructor
    public Vehicle() {}

    //create main constructor for sublclasses
    public Vehicle(String inBrand, double inSpeed, int inPassengers, double inCargo) {
        
        //assign brand, speed, passengers, weight
        brandName = inBrand;
        speed = inSpeed;
        passengers = inPassengers;
        cargoWeight = inCargo;
    }

    //return and set brand name
    public String getBrand() { return brandName; }
    public void setBrandName(String inBrand) { brandName = inBrand; }

    //return and set speed
    public double getSpeed() { return speed; }
    public void setSpeed(double inSpeed) { speed = inSpeed; }

    //return and set passengers
    public int getPassengers() { return passengers; }
    public void setPassengers(int inPassengers) { passengers = inPassengers; }

    //return and set weight
    public double getCargoWeight() { return cargoWeight; }
    public void setCargoWeight(double inCargoWeight) { cargoWeight = inCargoWeight; }

    //convert object info to string
    public String toString() {
        //print brand, speed, passengers, cargo
        return "Brand: " + brandName + "\n" +
               "Speed (mph): " + getSpeed() + "\n" +
               "Passengers: " + passengers + "\n" +
               "Cargo (lbs): " + cargoWeight + "\n";
    }
}

//car class inherits from vehicle
class Car extends Vehicle {

    //create variables for wheel, spoiler, stereo, mpg
    int wheels = 4;
    boolean spoiler = false;
    boolean stereo = false;
    double mpg = 0.0;

    //create car constructor
    public Car(String brand, double speed, int passengers, double cargo, double mpg) {
        //call vehicle constructor
        super(brand, speed, passengers, cargo);
        //assign mpg
        this.mpg = mpg;
    }

    //toggle spoiler and stereo
    public void setSpoiler(boolean s) { spoiler = s; }
    public void setStereo(boolean s) { stereo = s; }

    //override vehicle getSpeed
    @Override
    public double getSpeed() {
        
        //increase speed if spoiler is toggled
        if (spoiler)
            return super.getSpeed() + 10;
        else
            return super.getSpeed();
    }

    //return revised mpg
    public double getMPG() {
        
        //reduce mpg if stereo is toggled
        if (stereo)
            return mpg - (mpg * 0.10);
        else
            return mpg;
    }

    //override tostring
    @Override
    public String toString() {
        
        //print wheels, spoiler, stereo, mpg
        return super.toString() +
               "Wheels: 4\n" +
               "Spoiler: " + spoiler + "\n" +
               "Stereo: " + stereo + "\n" +
               "MPG: " + getMPG() + "\n";
    }
}

//sportscar inherits form car
class SportsCar extends Car {

    //create variable for max speed, horsepower
    double topSpeed;
    int horsepower;

    //constructor for sportscar
    public SportsCar(String brand, double speed, int passengers, double cargo, double mpg, double topSpeed, int hp) {
        //call car constructor
        super(brand, speed, passengers, cargo, mpg);
        //assign top speed
        this.topSpeed = topSpeed;
        //assign horsepower
        this.horsepower = hp;
    }
    
    //override getspeed method
    @Override
    public double getSpeed() {
        
        //return smaller value betweem current/topspeed
        return Math.min(super.getSpeed(), topSpeed);
    }

    //override tostring method
    @Override
    public String toString() {
        //print tpye, horsepower, top speed
        return super.toString() +
               "Type: Sports Car\n" +
               "Horsepower: " + horsepower + "\n" +
               "Top Speed: " + topSpeed + " mph\n";
    }
}
//familysuv inherits from car
class FamilySUV extends Car {

    //create variable for towing capacity
    double towingCapacity;

    //constructor for familysuv
    public FamilySUV(String brand, double speed, int passengers, double cargo, double mpg, double towCap) {
        //call car constructor
        super(brand, speed, passengers, cargo, mpg);
        //assign value to towing capacity
        towingCapacity = towCap;
    }

    //override tostring method
    @Override
    public String toString() {
        //print type, towing capacity
        return super.toString() +
               "Type: Family SUV\n" +
               "Towing Capacity: " + towingCapacity + " lbs\n";
    }
}

//sailboat inherits from vehicle
class SailBoat extends Vehicle {

    //create variable for max wind speed
    double maxWindSpeed;

    //sailboat constructor
    public SailBoat(String brand, double windSpeed) {
        //call vehicle constructor
        super(brand, windSpeed, 6, 2000);
        //assign windspeed
        maxWindSpeed = windSpeed;
    }

    //override tostring method
    @Override
    public String toString() {
        //print type
        return super.toString() +
               "Type: Sail Boat\n";
    }
}

//motorboat inherits from vehicle
class MotorBoat extends Vehicle {

    //create variable for horsepower, max speed
    double horsepower;
    double topSpeed;

    //constructor for motorboat
    public MotorBoat(String brand, double speed, double cargo, double hp, double topSpeed) {
        //call vehicle constructor
        super(brand, speed, 8, cargo);
        //assign horsepower
        horsepower = hp;
        //assign topspeed
        this.topSpeed = topSpeed;
    }

    //override getspeed
    @Override
    public double getSpeed() {
        //return smaller value between current and max speed
        return Math.min(super.getSpeed(), topSpeed);
    }

    //override tostring method
    @Override
    public String toString() {
        //print type, horsepower, topspeed
        return super.toString() +
               "Type: Motor Boat\n" +
               "Horsepower: " + horsepower + "\n" +
               "Top Speed: " + topSpeed + " mph\n";
    }
}

//passengerjet inherits from vehicle
class PassengerJet extends Vehicle {

    //create variable for cruise speed
    double cruiseSpeed = 550;

    //constructor for passengerjet
    public PassengerJet(String brand, int passengers, double cargo) {
        //call vehicle constructor
        super(brand, 550, passengers, cargo);
    }

    //override getspeed method
    @Override
    public double getSpeed() {
        //return cruise speed
        return cruiseSpeed;
    }

    //override tostring method
    @Override
    public String toString() {
        //print type
        return super.toString() +
               "Type: Passenger Jet\n";
    }
}

//fighterjet inherits from vehicle
class FighterJet extends Vehicle {

    //create variable for max speed
    double maxSpeed = 1500;

    //fighterjet constructor
    public FighterJet(String brand, int pilots, double cargo) {
        //call vehicle constructor
        super(brand, 1500, pilots, cargo);
    }

    //override getspeed method
    @Override
    public double getSpeed() {
        //return max speed
        return maxSpeed;
    }

    //override tostring method
    @Override
    public String toString() {
        //print type
        return super.toString() +
               "Type: Fighter Jet\n";
    }
}

//sportsmotorcycle inherits from vehicle
class SportMotorcycle extends Vehicle {

    //create variable for enginecc and maximum speed
    int engineCC;
    double topSpeed;

    //sportsmotorcycle constructor
    public SportMotorcycle(String brand, double speed, int engineCC, double topSpeed) {
        //call vehicle constructor
        super(brand, speed, 2, 100);
        //assign engine size
        this.engineCC = engineCC;
        //assign top speed
        this.topSpeed = topSpeed;
    }

    //override getspeed method
    @Override
    public double getSpeed() {
        //dont exceed topspeed
        return Math.min(super.getSpeed(), topSpeed);
    }

    //override tostring method
    @Override
    public String toString() {
        //print type, engine size, topspeed
        return super.toString() +
               "Type: Sport Motorcycle\n" +
               "Engine (cc): " + engineCC + "\n" +
               "Top Speed: " + topSpeed + " mph\n";
    }
}

//cruisermotorcycle inherits from vehicle
class CruiserMotorcycle extends Vehicle {

    //create variable for engine size
    int engineCC;

    //constructor for cruisermotorcycle
    public CruiserMotorcycle(String brand, double speed, int engineCC) {
        //call vehicle constructor
        super(brand, speed, 2, 150);
        //assign engine size
        this.engineCC = engineCC;
    }

    //override tostring method
    @Override
    public String toString() {
        //print type, enginesize
        return super.toString() +
               "Type: Cruiser Motorcycle\n" +
               "Engine (cc): " + engineCC + "\n";
    }
}