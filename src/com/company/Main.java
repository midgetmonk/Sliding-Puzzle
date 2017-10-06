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

        int boardLocation = 0; //Current location of " *"

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

            //Keeps track of what key " *" is placed in the HashMap boardValues
            if(possibleBoardValues[R] == " *"){
                boardLocation = x;
            }
        }

        Scanner reader = new Scanner(System.in);
        System.out.print("Welcome to Ryan's Sliding Puzzle! Please enter your move: \n");

        //While loop that traps the player until game is solved
        while(!boardValues.equals(solvedBoard)) {
            computePossibleMoves(boardValues, boardLocation);
            PrintBoard(boardValues, boardSize);
            nextMove = reader.nextInt();

            //Changes user input to a String
            if (nextMove < 10) {
                nextMoveAsString = " " + nextMoveAsString.valueOf(nextMove);
            } else {
                nextMoveAsString = nextMoveAsString.valueOf(nextMove);
            }

            //This block switches the value that the user entered with the adjacent " *"
            tempKey = computeKeyOfUserInput(nextMoveAsString, boardValues, boardLocation);
            tempValue = boardValues.get(tempKey);
            boardValues.replace(tempKey, " *");
            boardValues.replace(boardLocation, tempValue);
            boardLocation = tempKey;
        }


    }

    /*
    * PrintBoard takes the order of values that make up the current board as a HashMap and creates a X by X grid based on the boardSize
    * @param: Hashmap<Int, String> boardValues, int boardSize
    * @return: void
    */
    public static void PrintBoard(HashMap boardValues, int boardSize) {
        int startingPoint = 0;
        for(int x=0; x<boardSize; x++){
            for(int i=0; i<boardSize; i++){
                System.out.print(boardValues.get(startingPoint) + " ");
                startingPoint++;
            }
            System.out.println(); //new row
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
