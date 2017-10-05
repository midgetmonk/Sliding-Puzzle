package com.company;

import java.util.HashMap;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        HashMap<Integer,String> boardValues = new HashMap<>();

        final String[] possibleBoardValues = new String[] {" 1"," 2"," 3"," 4"," 5"," 6"," 7"," 8"," 9","10","11","12","13","14","15"," *"};
        int boardLocation = 0;

        Random rn = new Random();
        final int min = 0;
        final int max = 16;
        int R = rn.nextInt(max-min)+min;

        // For loop that creates a random number until a value that hasn't been entered comes up
        // Creates a random board
        for(int x=0; x<possibleBoardValues.length; x++){
            while(boardValues.containsValue(possibleBoardValues[R])) {
                R = rn.nextInt(max - min) + min;
            }
            boardValues.put(x, possibleBoardValues[R]);
        }

        PrintBoard(boardValues,0);

    }

    // For loop that iterates through the "boardValues" hashmap and prints the value to the screen
    public static void PrintBoard(HashMap boardValues, int boardLocation) {
        for(int x=0; x<4; x++){
            for(int i=0; i<4; i++){
                System.out.print(boardValues.get(boardLocation) + " ");
                boardLocation++;
            }

            System.out.println();
        }
    }
}
