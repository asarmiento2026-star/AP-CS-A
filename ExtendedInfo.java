/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.extendedinfo;
import java.util.*;
import java.io.*;
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

public class ExtendedInfo {

    //create contact class
    static class Contact {
        String name, email, username;
        int gradYear;

        public Contact(String name, String email, int gradYear, String username) {
            this.name = name;
            this.email = email;
            this.gradYear = gradYear;
            this.username = username;
        }

        public String toCSV() {
            return name + "," + email + "," + gradYear + "," + username;
        }

        public String toString() {
            return name + ", " + email + ", " + gradYear + ", " + username;
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();

        String folderName = "Contacts";
        String fileName = folderName + "/info.csv";

        File folder = new File(folderName);

        if (!folder.exists()) {
            folder.mkdir(); 
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    contacts.add(new Contact(
                        parts[0],
                        parts[1],
                        Integer.parseInt(parts[2]),
                        parts[3]
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing file found, starting fresh.");
        }

        char choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("a - Add contact");
            System.out.println("e - Order by email");
            System.out.println("y - Order by grad year");
            System.out.println("n - Order by name");
            System.out.println("q - Quit");
            System.out.print("Choice: ");

            choice = scan.nextLine().toLowerCase().charAt(0);

            switch (choice) {

                case 'a':
                    //your original input section reused
                    System.out.println("Enter Contact Details: ");
                    System.out.print("Name: ");
                    String name = scan.nextLine();

                    System.out.print("Email: ");
                    String email = scan.nextLine();

                    System.out.print("Graduation Year: ");
                    int gradYear = Integer.parseInt(scan.nextLine());

                    System.out.print("Username: ");
                    String username = scan.nextLine();

                    Contact c = new Contact(name, email, gradYear, username);
                    contacts.add(c);

                    try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
                        for (Contact contact : contacts) {
                            out.println(contact.toCSV());
                        }
                        System.out.println("[Success] Saved.");
                    } catch (IOException e) {
                        System.err.println("Error writing to file: " + e.getMessage());
                    }
                    break;

                case 'e':
                    contacts.sort(Comparator.comparing(contact -> contact.email));
                    display(contacts);
                    break;

                case 'y':
                    contacts.sort(Comparator.comparingInt(contact -> contact.gradYear));
                    display(contacts);
                    break;

                case 'n':
                    contacts.sort(Comparator.comparing(contact -> contact.name));
                    display(contacts);
                    break;

                case 'q':
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 'q');

        scan.close();
    }

    public static void display(ArrayList<Contact> contacts) {
        System.out.println("\nContacts:");
        for (Contact c : contacts) {
            System.out.println(c);
        }
    }
}
