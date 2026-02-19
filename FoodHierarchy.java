/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.foodhierarchy;

/**
 *
 * @author sarmi
 */
//create abstract class for product
abstract class Product {
    //create instance variable to store name
    private String name;

    //create constructor for product
    public Product(String name) {
        //set instance variable equal to parameter
        this.name = name;
    }

    //create getter method
    public String getName() {
        //return name value
        return name;
    }

    //decalare abstract method
    public abstract String getCategory();

    //override tostring method
    @Override
    public String toString() {
        //return descriptive sentence
        return name + " is a type of " + getCategory();
    }
}

//food class inherits from product
class Food extends Product {
    //create constructor that takes name parameter
    public Food(String name) {
        //call constructor of superclass
        super(name);
    }

    //override abstract method
    @Override
    public String getCategory() {
        //return default category
        return "General Food";
    }
}

//meat class inherits from food
class Meat extends Food {
    public Meat(String name) {
        //pass name to food constructor
        super(name);
    }

    @Override
    public String getCategory() {
        //return specific category
        return "Meat";
    }
}

//vegetable class inherits from food
class Vegetable extends Food {
    public Vegetable(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "Vegetable";
    }
}

//fruit calss inherits from food
class Fruit extends Food {
    public Fruit(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "Fruit";
    }
}

//grain class inherits from food
class Grain extends Food {
    public Grain(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "Grain";
    }
}

//dairy class inherits from food
class Dairy extends Food {
    public Dairy(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "Dairy";
    }
}

//fats and oils class inherits from food
class FatsAndOils extends Food {
    public FatsAndOils(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "Fats and Oils";
    }
}

//chicken is a type of meat
class Chicken extends Meat {
    public Chicken() {
        //send name to superclass constructor
        super("Chicken");
    }
}

//beef is a type of meat
class Beef extends Meat {
    public Beef() {
        super("Beef");
    }
}

//carrot is a type of vegetable
class Carrot extends Vegetable {
    public Carrot() {
        super("Carrot");
    }
}

//broccoli is a type of vegetable
class Broccoli extends Vegetable {
    public Broccoli() {
        super("Broccoli");
    }
}

//apple is a type of fruit
class Apple extends Fruit {
    public Apple() {
        super("Apple");
    }
}

//banana is a type of fruit
class Banana extends Fruit {
    public Banana() {
        super("Banana");
    }
}

//rice is a type of grain
class Rice extends Grain {
    public Rice() {
        super("Rice");
    }
}

//bread is a type of grain
class Bread extends Grain {
    public Bread() {
        super("Bread");
    }
}

//milk is a type of dairy
class Milk extends Dairy {
    public Milk() {
        super("Milk");
    }
}

//cheese is a type of dairy
class Cheese extends Dairy {
    public Cheese() {
        super("Cheese");
    }
}

//butter is a type of fat
class Butter extends FatsAndOils {
    public Butter() {
        super("Butter");
    }
}

//olive oil is a type of oil
class OliveOil extends FatsAndOils {
    public OliveOil() {
        super("Olive Oil");
    }
}

//create public class with main method
public class FoodHierarchy {
    //create main method
    public static void main(String[] args) {

        //create array of product references
        Product[] products = {
            new Chicken(),
            new Beef(),
            new Carrot(),
            new Broccoli(),
            new Apple(),
            new Banana(),
            new Rice(),
            new Bread(),
            new Milk(),
            new Cheese(),
            new Butter(),
            new OliveOil()
        };

        //use for loop to go through each product
        for (Product foodProduct : products) {
            //print each object
            System.out.println(foodProduct);
        }
    }
}