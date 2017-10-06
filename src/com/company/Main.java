package com.company;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HashMap<Integer,String> boardValues = new HashMap<>();
        HashMap<Integer,String> solvedBoard = new HashMap<>();

        final String[] possibleBoardValues = new String[] {" 1"," 2"," 3"," 4"," 5"," 6"," 7"," 8"," 9","10","11","12","13","14","15"," *"};

        for(int x = 0; x<16; x++){
            solvedBoard.put(x, possibleBoardValues[x]);
        }

        int boardLocation = 0;

        Random rn = new Random();
        final int min = 0;
        final int max = 16;
        final int boardSize = 4;
        String tempValue = new String();
        int tempKey = 0;
        int nextMove;
        String nextMoveAsString = new String();
        int R = rn.nextInt(max-min)+min;

        // For loop that creates a random number until a value that hasn't been entered comes up
        // Creates a random board
        for(int x=0; x<possibleBoardValues.length; x++){
            while(boardValues.containsValue(possibleBoardValues[R])) {
                R = rn.nextInt(max - min) + min;
            }
            boardValues.put(x, possibleBoardValues[R]);

            if(possibleBoardValues[R] == " *"){
                boardLocation = x;
            }
        }

        Scanner reader = new Scanner(System.in);
        System.out.print("Welcome to Ryan's Sliding Puzzle! Please enter your move: \n");

        while(!boardValues.equals(solvedBoard)) {
            computePossibleMoves(boardValues, boardLocation);
            PrintBoard(boardValues, 0, boardSize);
            nextMove = reader.nextInt();

            if (nextMove < 10) {
                nextMoveAsString = " " + nextMoveAsString.valueOf(nextMove);
            } else {
                nextMoveAsString = nextMoveAsString.valueOf(nextMove);
            }

            tempKey = computeKeyOfUserInput(nextMoveAsString, boardValues, boardLocation);
            tempValue = boardValues.get(tempKey);
            boardValues.replace(tempKey, " *");
            boardValues.replace(boardLocation, tempValue);
            boardLocation = tempKey;
            System.out.println(boardLocation);

            //PrintBoard(boardValues, 0, boardSize);
        }


    }

    // For loop that iterates through the "boardValues" hashmap and prints the value to the screen
    public static void PrintBoard(HashMap boardValues, int boardLocation, int boardSize) {
        for(int x=0; x<boardSize; x++){
            for(int i=0; i<boardSize; i++){
                System.out.print(boardValues.get(boardLocation) + " ");
                boardLocation++;
            }

            System.out.println();
        }
    }

    public static void computePossibleMoves(HashMap boardValues, int boardLocation){
        System.out.println("Possible Moves: ");

        if(boardLocation % 4 == 0){
            System.out.print(boardValues.get(boardLocation+1) + " ");
        }
        if(boardLocation-4 >= 0){
            System.out.print(boardValues.get(boardLocation-4) + " ");
        }
        if(boardLocation+4 <= 15){
            System.out.print(boardValues.get(boardLocation+4) + " ");
        }
        if(boardLocation == 3 || boardLocation == 7 || boardLocation == 11 || boardLocation == 15){
            System.out.print(boardValues.get(boardLocation-1) + " ");
        }
        if(boardLocation % 4 != 0 && boardLocation != 3 && boardLocation != 7 && boardLocation != 11 && boardLocation != 15){
            System.out.print(boardValues.get(boardLocation-1) +  " " + boardValues.get(boardLocation + 1) + " ");
        }

        System.out.println("\n");
    }

    public static int computeKeyOfUserInput(String nextMoveAsString, HashMap boardValues, int boardLocation){
        int key = boardLocation;
        if(nextMoveAsString.equals(boardValues.get(boardLocation-4))){
            key = boardLocation - 4;
        }else if(nextMoveAsString.equals(boardValues.get(boardLocation - 1))){
            key = boardLocation - 1;
        }else if(nextMoveAsString.equals(boardValues.get(boardLocation + 1))){
            key = boardLocation + 1;
        }else if(nextMoveAsString.equals(boardValues.get(boardLocation + 4))){
            key = boardLocation + 4;
        }

        return key;
    }
}
