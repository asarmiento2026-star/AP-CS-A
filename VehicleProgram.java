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
        FlyingCar flyingcar = new FlyingCar("Flying Car", 200, 2, 300, 15, 5);
        System.out.println(flyingcar);

        //create and print object of familysuv
        ElectricCar tesla = new ElectricCar("Tesla", 120, 7, 800, 25, 7200);
        System.out.println(tesla);

        //create and print object of sailboat
        SailBoat sail = new SailBoat("Sail Boat", 2);
        System.out.println(sail);

        //create and print object of speedboat
        RowBoat rowBoat = new RowBoat("Row Boat", 15, 1000, 2);
        System.out.println(rowBoat);

        //create and print object of passengerjet
        SeaPlane seaPlane = new SeaPlane("Sea Plane", 2, 1000, 2);
        System.out.println(seaPlane);

        //create and print object of fighterjet
        CargoPlane cargoPlane = new CargoPlane("Cargo Plane", 1, 20000, "Heavy Duty");
        System.out.println(cargoPlane);

        //create and print object of sportmotorcycle
        SportMotorcycle sportMotorcycle = new SportMotorcycle("Sports Bike", 180, 998, 24);
        System.out.println(sportMotorcycle);

        //create and print object of cruisermotorcycle
        DirtBike dirtBike = new DirtBike("Dirt Bike", 110, 1746, "Knobby");
        System.out.println(dirtBike);
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

//flyingcar inherits form car
class FlyingCar extends Car {

    //create variable for wings
    int fins;

    //constructor for flyingcar
    public FlyingCar(String brand, double speed, int passengers, double cargo, double mpg, int fins) {
        //call car constructor
        super(brand, speed, passengers, cargo, mpg);
        //assign fins
        this.fins = fins;
    }

    //override tostring method
    @Override
    public String toString() {
        //print tpye, horsepower, top speed
        return super.toString() +
               "Type: Flying Car\n" +
               "Fins: " + fins + "\n";
    }
}
//electriccar inherits from car
class ElectricCar extends Car {

    //create variable for towing capacity
    int batteryPack;

    //constructor for electriccar
    public ElectricCar(String brand, double speed, int passengers, double cargo, double mpg, int batteryPack) {
        //call car constructor
        super(brand, speed, passengers, cargo, mpg);
        //assign value to batteryPack
        this.batteryPack = batteryPack;
    }

    //override tostring method
    @Override
    public String toString() {
        //print type, towing capacity
        return super.toString() +
               "Type: Electric Car\n" +
               "Battery Cells: " + batteryPack + " batteries\n";
    }
}

//sailboat inherits from vehicle
class SailBoat extends Vehicle {

    //create variable for max wind speed
    int sails;

    //sailboat constructor
    public SailBoat(String brand, int sails) {
        //call vehicle constructor
        super(brand, sails, 6, 2000);
        //assign windspeed
        this.sails = sails;
    }

    //override tostring method
    @Override
    public String toString() {
        //print type, sails
        return super.toString() +
               "Type: Sail Boat\n" +
               "Sails: " + sails + "\n";
    }
}

//rowboat inherits from vehicle
class RowBoat extends Vehicle {

    //create variable for horsepower, max speed
    int oars = 2;

    //constructor for rowboat
    public RowBoat(String brand, double speed, double cargo, int oars) {
        //call vehicle constructor
        super(brand, speed, 2, 200.0);
        //assign oars
        this.oars = oars;
    }

    //override tostring method
    @Override
    public String toString() {
        //print type, horsepower, topspeed
        return super.toString() +
               "Type: Row Boat\n" +
               "Horsepower: " + oars + "\n";
    }
}

//seaplane inherits from vehicle
class SeaPlane extends Vehicle {

    //create variable for cruise speed
    int pontoons = 2;

    //constructor for seaplane
    public SeaPlane(String brand, int passengers, double cargo, int pontoons) {
        //call vehicle constructor
        super(brand, 160, passengers, cargo);
    }

    //override tostring method
    @Override
    public String toString() {
        //print type
        return super.toString() +
               "Type: Sea Plane\n" +
               "Pontoons: " + pontoons + "\n";
    }
}

//cargoplane inherits from vehicle
class CargoPlane extends Vehicle {

    //create variable for landing gear
    String landingGear = "Heavy Duty";

    //cargoplane constructor
    public CargoPlane(String brand, int pilots, double cargo, String landingGear) {
        //call vehicle constructor
        super(brand, 600, pilots, cargo);
    }

    //override tostring method
    @Override
    public String toString() {
        //print type and landing gear
        return super.toString() +
               "Type: Cargo Plane\n" +
               "Landing Gear: " + landingGear + "\n";
    }
}

//sportsmotorcycle inherits from vehicle
class SportMotorcycle extends Vehicle {

    //create variable for enginecc and maximum speed
    int engineCC;
    int fairings;

    //sportsmotorcycle constructor
    public SportMotorcycle(String brand, double speed, int engineCC, int fairings) {
        //call vehicle constructor
        super(brand, speed, 2, 100);
        //assign engine size
        this.engineCC = engineCC;
        //assign fairings
        this.fairings = fairings;
    }

    //override tostring method
    @Override
    public String toString() {
        //print type, engine size, topspeed
        return super.toString() +
               "Type: Sport Motorcycle\n" +
               "Engine (cc): " + engineCC + "\n" +
               "Fairings: " + fairings + "\n";
    }
}

//dirtbike inherits from vehicle
class DirtBike extends Vehicle {

    //create variable for engine size, tire type
    int engineCC;
    String tireType;

    //constructor for dirtbike
    public DirtBike(String brand, double speed, int engineCC, String tireType) {
        //call vehicle constructor
        super(brand, speed, 2, 150);
        //assign engine size
        this.engineCC = engineCC;
        this.tireType = tireType;
    }

    public String getTireType() {return tireType;}
    
    //override tostring method
    @Override
    public String toString() {
        //print type, enginesize
        return super.toString() +
               "Type: Dirt Bike\n" +
               "Engine (cc): " + engineCC + "\n" +
               "Tire Type: " + tireType + "\n";
    }
}