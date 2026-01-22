/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.classrelationships;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author sarmi
 */

//set up an interface called priority
interface Priority {
    //create method to set priority value
    void setPriority(int priority);
    //create method to get priorty value
    int getPriority();
}

//implement the complexity interface
interface Complexity {
    //create method to set complexity value
    void setComplexity(int complexity);
    //create method to get complexity value
    int getComplexity();
}

//design a class called task and implement prioirty, cojmplexity, and comparable interfaces
class Task implements Priority, Complexity, Comparable<Task> {

    //instance variable to store task name
    private String name;
    //instance variable to store task priority
    private int priority;
    //instance variable to store task complexity
    private int complexity;

    //constructor initializes instance variables
    public Task(String taskName, int taskPriority, int taskComplexity) {
        //assign task name
        name = taskName;
        //assign priority
        priority = taskPriority;
        //assign complexity
        complexity = taskComplexity;
    }

    //set priority value
    public void setPriority(int newPriority) {
        //update priority
        priority = newPriority;
    }

    //return priority value
    public int getPriority() {
        return priority;
    }

    //set complexity value
    public void setComplexity(int newComplexity) {
        //update complexity
        complexity = newComplexity;
    }

    //return complexity value
    public int getComplexity() {
        return complexity;
    }

    //compare tasks by priority
    public int compareTo(Task other) {

        //check for lower priority of task
        if (priority < other.priority) {
            //will come before
            return -1;
        }
        
        //check for higher priority of task
        else if (priority > other.priority) {
            //will come after
            return 1;
        }
        
        //if same, compare complexity
        else {
            //check for lower complexity of task
            if (complexity < other.complexity) {
                //will come before
                return -1;
            }
            
            //check for higher complexity of task
            else if (complexity > other.complexity) {
                //will come after
                return 1;
            }
            //if all are equal, tasks are equal
            else {
                return 0;
            }
        }
    }

    //create a string to print the task object
    public String toString() {
        //name, priority, complexity
        return "Task: " + name + " | Priority: " + priority + " | Complexity: " + complexity;
    }
}

public class ClassRelationships {

    public static void main(String[] args) {

        //arraylist to store task objects
        ArrayList<Task> tasks = new ArrayList<Task>();

        //tasks that will be contained in the arraylist
        tasks.add(new Task("Math Homework", 2, 3));
        tasks.add(new Task("Study for AP CSA", 1, 2));
        tasks.add(new Task("Clean Room", 3, 1));
        tasks.add(new Task("College Application", 1, 5));

        //use compareto method to sort
        Collections.sort(tasks);

        //print out the list and list info
        System.out.println("Tasks ranked by priority, then complexity:\n");

        //loop the list until each task is printed
        for (Task t : tasks) {
            //cakk tostring method
            System.out.println(t);
        }
    }
}