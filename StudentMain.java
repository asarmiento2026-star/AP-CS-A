/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentmain;

/**
 *
 * @author ASarmiento2026
 */

//I supply a very basic “skeleton code” for a Student Class
//Often in jobs in IT you will be given the “high level” Class to work on
//You need to make it work properly - add features and so on
//I want you to alter this base Class as follows:
//Each student object should have 3 courses
//Each course will have a test
//Provide constructors (overloaded) for student/course objects
//Each test score will be initialized to zero
//Provide methods to  setTestScore and getTestScore for each course
//Provide a method called getAverage that computes this
//Alter toString to provide the student details
//Modify the driver Class to allow adding students and getting their scores
//Provide a planning doc to show your logic in how you design the classes/relationships
//Provide a simple UML Class Diagram to show the relationships, and where the public/private methods are

public class StudentMain {
    public static void main(String[] args) {

        //create a student with a first/last name
        Student st1 = new Student("Bob", "Smith");

        //create test scores for three courses
        st1.setTestScore(0, 77);   // Course 1
        st1.setTestScore(1, 83);   // Course 2
        st1.setTestScore(2, 65);   // Course 3

        //print the students information
        System.out.println(st1);

        //print the students average
        System.out.println("Average Score: " + st1.getAverage());
    }
}

//design student class
class Student {

    //instance variable to store first name
    private String firstName;
    private String lastName;
    //instance variable to store last name
    private Course[] courses;
    //instance variable to store courses (x3)

    //constructor initializes instance variables
    public Student() {
        //empty first name
        firstName = "";
        //empty last name
        lastName = "";
        //empty course and create array
        courses = new Course[3];

        //create course objects in array
        for (int i = 0; i < courses.length; i++) {
            //set course score to zero
            courses[i] = new Course();
        }
    }

    //create a student with first and last name
    public Student(String first, String last) {
        firstName = first;
        lastName = last;
        //create an array with three empty courses
        courses = new Course[3];

        //fill array with course objects
        for (int i = 0; i < courses.length; i++) {
            courses[i] = new Course();
        }
    }

    //set test score for a course
    public void setTestScore(int courseNumber, int score) {
        courses[courseNumber].setTestScore(score);
    }

    //get test score a course
    public int getTestScore(int courseNumber) {
        return courses[courseNumber].getTestScore();
    }

    //calculate the average of test scores
    public double getAverage() {
        //store total of all test scores
        int total = 0;

        //add test scores together
        for (int i = 0; i < courses.length; i++) {
            total += courses[i].getTestScore();
        }

        //divide by number of courses to get average
        return total / 3.0;
    }

    //return string with student info
    public String toString() {
        //student name
        String result = firstName + " " + lastName + "\n";

        //courses w/ test scores
        for (int i = 0; i < courses.length; i++) {
            result += "Course " + (i + 1) + " Test Score: "
                    + courses[i].getTestScore() + "\n";
        }

        //return when complete
        return result;
    }
}

//design course class
class Course {

    //instance variable to store test score
    private int testScore;

    //constructor initializes instance variables
    public Course() {
        //start test score at zero
        testScore = 0;
    }

    //set the test score per course
    public void setTestScore(int score) {
        testScore = score;
    }

    //get the test score per course
    public int getTestScore() {
        return testScore;
    }
}