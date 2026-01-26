/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.radiuscalc;
import java.util.Scanner;

public class RadiusCalc {

    /**
     * @param args the command line arguments
     */
    //carry out calculations or circular items
    //such as a circle, a sphere, a cone, a column
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("This program calculates round item numbers such as \n" +
                "1 - Area of a circle (pi r^2)\n" +
                "2 - Volume of a sphere (4/3 pi r^3)\n" +
                "3 - Volume of a cone (pi r^2 h/3)\n" +
                "4 - Volume of a column (pi r^2 h)");
        System.out.println("Type the number for which one you want to calculate");
        
        if (!s.hasNextInt()) {
            System.out.println("Enter a number between 1 and 4, try again");
            return;
        }
        
        int i = s.nextInt();
        if (i == 1){
            cCalc1();
        }else if (i == 2){
            cCalc2();
        }else if(i == 3){
            cCalc3();
        }else if(i == 4){
            cCalc4();
        } else {
            System.out.println("Enter a number between 1 and 4, try again");
        }
    }
    
    public static void cCalc1(){
        Scanner s = new Scanner(System.in);
        System.out.println("enter the radius of your circle");
        
        if (!s.hasNextDouble()) {
            System.out.println("enter a number, try again");
            return;
        }
        
        double r = s.nextDouble();
        double result = Math.PI * Math.pow(r, 2);
        double round = Math.round(result*100.0) / 100.0;
        System.out.println(round);
    }
    
    public static void cCalc2(){
        Scanner s = new Scanner(System.in);
        System.out.println("enter the radius of your sphere");
        
        if (!s.hasNextDouble()) {
            System.out.println("enter a number, try again");
            return;
        }
        
        double r = s.nextDouble();
        double result = ((Math.PI * Math.pow(r, 3)) * (4.0/3.0));
        double round = Math.round(result*100.0) / 100.0;
        System.out.println(round);
    }
    
    public static void cCalc3(){
        Scanner s = new Scanner(System.in);
        System.out.println("enter the radius of your cone, then height of your cone");
        
        if (!s.hasNextDouble()) {
            System.out.println("enter a number, try again");
            return;
        }
        
        double r = s.nextDouble();
        
        if (!s.hasNextDouble()) {
            System.out.println("enter a number, try again");
            return;
        }
        
        double h = s.nextDouble();
        double result = (Math.PI * Math.pow(r, 2)) * (h / 3);
        double round = Math.round(result*100.0) / 100.0;
        System.out.println(round);
    }
    
    public static void cCalc4(){
        Scanner s = new Scanner(System.in);
        System.out.println("enter the radius of your column, then height of your column");
        
        if (!s.hasNextDouble()) {
            System.out.println("enter a number, try again");
            return;
        }
        
        double r = s.nextDouble();
        
        if (!s.hasNextDouble()) {
            System.out.println("enter a number, try again");
            return;
        }
        
        double h = s.nextDouble();
        double result = (Math.PI * Math.pow(r, 2)) * h;
        double round = Math.round(result*100.0) / 100.0;
        System.out.println(round);
    }
}
