package com.company;

import java.util.Scanner;

public class Main {

    //int[] board = new int[9];

    static int[] board = {
            0, 0, 0,
            0, 0, 0,
            0, 0, 0
    };

    static boolean p1 = true;
    static boolean p2 = false;

    static void displayBoard(){

        char[] displayBoardArray = new char[9];

        for(int i=0; i<9; i++){
            if(board[i]==0)
                displayBoardArray[i] = ' ';
            else if(board[i]==1)
                displayBoardArray[i]= 'X';
            else if(board[i]==2)
                displayBoardArray[i] = 'O';
        }

        System.out.println("---------");
        System.out.println(displayBoardArray[0] + " | " + displayBoardArray[1] + " | " + displayBoardArray[2]);
        System.out.println("---------");
        System.out.println(displayBoardArray[3] + " | " + displayBoardArray[4] + " | " + displayBoardArray[5]);
        System.out.println("---------");
        System.out.println(displayBoardArray[6] + " | " + displayBoardArray[7] + " | " + displayBoardArray[8]);
        System.out.println("---------");
    }

    static boolean isBoardFull(){
        boolean isFull = true;
        for (int i=0; i<9; i++){
            if(board[i]==0)
                isFull = false;
        }
        return isFull;
    }

    static void getUserInput(){

         int inputPosition;
         Scanner scanner = new Scanner(System.in);

         if(p1){
             System.out.print("Player 1 Enter position to insert X: ");
         }
         else if(p2){
             System.out.print("Player 2 Enter position to insert O: ");
         }

         //TODO Error Checking on this user input
         inputPosition = scanner.nextInt();

        inputPosition--;

         if(p1 && board[inputPosition]==0){
             board[inputPosition] = 1;
             //Swap player
             p1 = false;
             p2 = true;
         }
         else if(p2 && board[inputPosition]==0){
             board[inputPosition] = 2;
             //Swap player
             p1 = true;
             p2 = false;
         }
         else{
             System.out.println("Position already full, try again");
             getUserInput();
         }

     }

    static boolean isGameOver(){

         // check win by row
         for (int i = 0; i < 9; i += 3) {
             if ((board[i] == board[i+1]) && (board[i+1] == board[i+2])) {
                 if (board[i] == 1)  {
                     System.out.println("Player 1 wins!");
                     return true;
                 }
                 else if (board[i] == 2) {
                     System.out.println("Player 2 wins!");
                     return true;
                 }
             }
         }

         // check win by column
         for (int j = 0; j < 3; j++) {
             if ((board[j] == board[j+3]) &&  (board[j+3] == board[j+6])) {
                 if (board[j] == 1) {
                     System.out.println("Player 1 wins!");
                     return true;
                 }
                 else if (board[j] == 2) {
                     System.out.println("Player 2 wins!");
                     return true;
                 }
             }
         }

         // check win by diagonal
         if (((board[0] == board[4]) && (board[4] == board[8])) ||
                 ((board[2] == board[4]) && (board[4] == board[6]))) {
             if (board[4] == 1) {
                 System.out.println("Player 1 wins!");
                 return true;
             }
             else if (board[4] == 2) {
                 System.out.println("Player 2 wins!");
                 return true;
             }
         }

         if(isBoardFull()){
             System.out.println("It is a draw!");
             return true;
         }

         return false;
     }

    static void resetGame(){
        for(int i=0; i<9; i++)
            board[i] = 0;
        p1 = true;
        p2 = false;
    }

    static boolean endGame(){

        String x;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to start another game? Y/N: ");

        // TODO Error Checking on this input
        x = scanner.nextLine();

        switch(x){
            case "y":
            case "Y":
                resetGame();
                return true;
            case "n":
            case "N":
                return false;
            default:
                System.out.println("Invalid input, please try again");
                endGame();
        }

        return false;
    }

    public static void main(String[] args) {
	// write your code here

        boolean continueGame = true;

        while(continueGame){
            while(!isBoardFull() && !isGameOver()){
                displayBoard();
                getUserInput();
            }
            displayBoard();
            continueGame = endGame();
        }

        System.out.println("Thank you for playing :)");

    }
}
