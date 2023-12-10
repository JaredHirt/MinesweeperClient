/**
 * This file is a driver program for a minesweeper server application
 * UNBC COMPUTER SCIENCE CLUB 2023
 * EXPLANATION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * The Server will send you the state of the game
 * There is already the convertInput method to convert the input to a 2d array
 * You are tasked with creating a bot to perform the best at minesweeper
 *You are to provide an x input, then a y input
 * The ranges for inputs are:
 * 0-15 (left to right x-axis)
 * 0-15 (top to bottom y-axis)
 * The server input is classified as follows:
 * 0-8 How many bombs surround that tile
 * -2 unexplored tile
 * -1 bomb
 * <p>
 * Good Luck
 * Please enter in your information below
 * <p>
 * Student Name:
 * Student Number:
 * Date:
 * Year of Study:
 */

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4999);
        PrintWriter sendToServer = new PrintWriter(socket.getOutputStream());

        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        Scanner serverIn = new Scanner(in);
        String[] boardState = new String[16];
        int[] input;
        while(socket.isConnected()){
            //Getting state of game from server
            if(serverIn.hasNext()) {
                for(int i = 0; i < 16; i++)
                   boardState[i] = serverIn.nextLine();
               input = getInput(convertInput(boardState));
                sendToServer.println(input[0]);
                sendToServer.flush();
                sendToServer.println(input[1]);
                sendToServer.flush();
            }
        }
    }

    /**
     * Converts the input from the server into a 2d array of integers
     * @param arr the array of strings from the server
     * @return the 2d array which represents the game state of the board
     */
    public static int[][] convertInput(String[] arr){
        int[][] returnArr = new int[arr.length][];
        for(int i = 0; i < arr.length; i++){
            returnArr[i] = Arrays.stream(arr[i].split(",")).mapToInt(Integer::parseInt).toArray();
        }
        return returnArr;
    }

    /**
     * This method is the student's job to implement
     * when this method is called it will be given the state of the board
     * you are tasked with returning an array of integers size 2 with the x input being in index 0, and the y input being in index 1
     * @param board the state of the game
     * @return The tile you want to reveal (x then y)
     */
    public static int[] getInput(int[][] board){
       int[] move = new int[2];
       Random rand = new Random();
       move[0] = rand.nextInt(16);
       move[1] = rand.nextInt(16);
       return move;
    }
}