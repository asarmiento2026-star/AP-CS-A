/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.information;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author ASarmiento2026
 */
public class Information {

    public static void main(String[] args) {
        //initialize scanner
        Scanner scan = new Scanner(System.in);
        String folderName = "Contacts";
        String fileName = folderName + "/info.txt";

        //create File class object to represent directory path
        File folder = new File(folderName);

        //develop Logic to ensure the directory is valid
        if (!folder.exists()) {
            //create the directory named "Contacts"
            folder.mkdir(); 
        }
        
        //prompt the user to enter details and scan input
        System.out.println("Enter Contact Details: ");
        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Graduation Year: ");
        String gradYear = scan.nextLine();
        System.out.print("Username: ");
        String username = scan.nextLine();

        //use try to close file incase error occurs
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
            //move data to file
            out.println(name + ", " + email + ", " + gradYear + ", " + username);
            out.println("\n");
            System.out.println("\n[Success] Data written to " + fileName);
            
        //use catch to overcome issues (permissions/directories)
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        //read file until there are no more lines
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
            
    //if file does not exist
    } catch (IOException e) {
        //run if file error
        System.err.println("An error occurred: " + e.getMessage());
            } finally {

        //close console scanner
        scan.close();
        }
    }
}
