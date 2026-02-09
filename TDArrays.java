/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tdarrays;

import java.util.Random;

//Write a 2D String array called deckOfCards. 
//This will have 4 arrays, each of length 13. 
//Fill the 4 arrays with the 4 suits of playing cards, from Ace to King (1 to 13). 
//Shuffle the deck, and deal out 4 hands of 5 cards from this 2D array (no dealing the same card twice). 
//Show the hands (Diamonds, Hearts, Clubs, Spades).

/**
 *
 * @author ASarmiento2026
 */
public class TDArrays {

    public static void main(String[] args) {
        
        //array with the four suits
        String[] suits = {"Diamonds", "Hearts", "Clubs", "Spades"};
        //array with the thirteen ranks
        String[] ranks = {
                "Ace", "Two", "Three", "Four", "Five", "Six", "Seven",
                "Eight", "Nine", "Ten", "Jack", "Queen", "King"
        };
        
        //2d array representing the deck
        String[][] deckOfCards = new String[4][13];

        //fill deck with card combinations
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                deckOfCards[i][j] = ranks[j] + " of " + suits[i];
            }
        }

        //track cards that have been selected
        boolean[][] picked = new boolean[4][13];

        //store deck with suit/rank indexes
        int[][] shuffledDeck = new int[52][2];

        //use rand to shuffle
        Random rand = new Random();

        //shuffle without repeats
        for (int i = 0; i < 52; i++) {
            int randSuit = rand.nextInt(4);
            int randRank = rand.nextInt(13);

            //shuffle until new card
            while (picked[randSuit][randRank]) {
                randSuit = rand.nextInt(4);
                randRank = rand.nextInt(13);
            }

            //store cards by suit/rank
            shuffledDeck[i][0] = randSuit;
            shuffledDeck[i][1] = randRank;
            
            //mark selected card
            picked[randSuit][randRank] = true;
        }

        //track card that is dealt
        int cardIndex = 0;

        //deal 4 hands w/ 5 cards
        for (int hand = 0; hand < 4; hand++) {
            //display hand
            System.out.println(suits[hand] + " Hand:");

            //deal 5 cards for each hand
            for (int card = 0; card < 5; card++) {
                int suitIndex = shuffledDeck[cardIndex][0];
                int rankIndex = shuffledDeck[cardIndex][1];

                //display card
                System.out.println("  " + deckOfCards[suitIndex][rankIndex]);
                //move onto the next card
                cardIndex++;
            }
            //spacing
            System.out.println();
        }
    }
}