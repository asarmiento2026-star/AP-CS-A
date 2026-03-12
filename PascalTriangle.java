/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pascaltriangle;
import java.util.Scanner;

/**
 *
 * @author ASarmiento2026
 */
public class PascalTriangle {

    // Recursive method to generate the nth row
    static void pascal(int n, int[] row) {
        if (n == 0) {      // Base case
            row[0] = 1;
            return;
        }

        int[] prev = new int[n];   // Array for previous row
        pascal(n - 1, prev);       // Recursive call

        row[0] = 1;
        row[n] = 1;

        for (int i = 1; i < n; i++) {
            row[i] = prev[i - 1] + prev[i];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter N: ");
        int n = sc.nextInt();

        int[] row = new int[n + 1];

        pascal(n, row);

        System.out.println("Row " + n + " of Pascal's Triangle:");
        for (int i = 0; i <= n; i++) {
            System.out.print(row[i] + " ");
        }

        sc.close();
    }
}