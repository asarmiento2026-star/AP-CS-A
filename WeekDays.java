/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.weekdays;
import java.util.Random;
/**
 *
 * @author sarmi
 */
//create WeekDays class
public class WeekDays {
    public static void main(String[] args) {

        //create and initialize array with all days of week
        String[] weekDays = {
            "Monday", 
            "Tuesday", 
            "Wednesday", 
            "Thursday", 
            "Friday", 
            "Saturday", 
            "Sunday"
        };

        //prompt to print all of the days
        System.out.println("All days of the week:");

        //use loop to print one day per row
        for (int i = 0; i < weekDays.length; i++) {
            System.out.println(weekDays[i]);
        }

        //resizing
        //make new array to hold weekdays only
        String[] weekdaysOnly = new String[5];

        //copy first five weekdays into array
        for (int i = 0; i < weekdaysOnly.length; i++) {
            weekdaysOnly[i] = weekDays[i];
        }

        //prompt to print all of the weekdays
        System.out.println("\nWeekdays only:");

        //use loop to print one weekday per row
        for (int i = 0; i < weekdaysOnly.length; i++) {
            System.out.println(weekdaysOnly[i]);
        }

        //reference shuffle method and shuffle weekdays array
        shuffleArray(weekdaysOnly);

        //prompt to print weekdays shuffled
        System.out.println("\nShuffled weekdays:");

        //use loop to print one random weekday per row
        for (int i = 0; i < weekdaysOnly.length; i++) {
            System.out.println(weekdaysOnly[i]);
        }
    }

    //create shuffle method
    public static void shuffleArray(String[] arr) {
        //create random object
        Random rand = new Random();

        //loop through arrays based off index
        for (int i = arr.length - 1; i > 0; i--) {

            //pick index from 0 to i
            int randomIndex = rand.nextInt(i + 1);

            //creat temporary value to store index
            String temp = arr[i];

            //swap values
            arr[i] = arr[randomIndex];
            arr[randomIndex] = temp;
        }
    }
}
