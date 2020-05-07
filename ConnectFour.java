import java.util.Arrays;
import java.util.Scanner;

public class ConnectFour {
    public static int rows = 6, columns = 7;
    public static char[][] gameBoard = new char[rows][columns];

    //Creates a new game board and initializes all the values of the 2d array with blank characters
    public static void initializeBoard(){
        for(int i = 0; i < rows; i++){
            Arrays.fill(gameBoard[i], ' ');
        }
    }

    //Prints out a new gameBoard with the updated values from the gameBoard array
    public static void printBoard(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                System.out.print("| " + gameBoard[i][j]+ " |");
            }
            System.out.println();
        }

        for(int i = 0; i < 3; i++)
            System.out.println();
    }

    //Inputs a disk depending on the input column and the character value(player)
    public static void inputDisk(char val, int game_column){
        for(int i = 5; i >= 0; i--) {
            if (gameBoard[i][game_column] == ' ') {
                gameBoard[i][game_column] = val;
                break;
            }
        }
    }

    //Changes the player depending on who is currently playing
    public static char changePlayer(char currentlyPlaying){
        if(currentlyPlaying == 'R')
            return 'Y';
        else
            return 'R';
    }

    //checks for all possible ways to win in the gameBoard array, and returns true is a winner has been found
    public static boolean checkStatus(char val){

        //check for horizontal win
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns - 3; j++){
                if(gameBoard[i][j] == val && gameBoard[i][j] == gameBoard[i][j+1] && gameBoard[i][j] == gameBoard[i][j+2] && gameBoard[i][j] == gameBoard[i][j+3]){
                    return true;
                }
            }
        }

        //check for vertical win
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows - 3; j++){
                if(gameBoard[j][i] == val && gameBoard[j][i] == gameBoard[j+1][i] && gameBoard[j][i] == gameBoard[j+2][i] && gameBoard[j][i] == gameBoard[j+3][i]){
                    return true;
                }
            }
        }

        //check for diagonal win from top left corner to bottom right corners
        for(int i = 0; i < rows - 3; i++){
            for(int j = 0; j < columns - 3; j++){
                if (gameBoard[i][j] == val && gameBoard[i][j] == gameBoard[i+1][j+1] && gameBoard[i][j] == gameBoard[i+2][j+2] && gameBoard[i][j] == gameBoard[i+3][j+3]) {
                    return true;
                }
            }
        }

        //check for secondary diagonal win from bottom left corner to top right corners
        for(int i = 0; i < rows - 3; i++){
            for(int j = 3; j < columns; j++){
                if (gameBoard[i][j] == val && gameBoard[i][j] == gameBoard[i+1][j-1] && gameBoard[i][j] == gameBoard[i+2][j-2] && gameBoard[i][j] == gameBoard[i+3][j-3]) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args){

        Scanner stdin = new Scanner(System.in);
        String currentPlayer = "Red";
        char player = 'R';

        initializeBoard();
        printBoard();

        while(true){

            System.out.print("Drop a " + currentPlayer + " disk at column (0-6): "); int column = stdin.nextInt();

            //In case an input is outside of the allowed range from 0 - 6, this will re-prompt the user to enter another value
            while(column > 6 || column < 0){
                System.out.print("Drop a " + currentPlayer + " disk at column (0-6): ");
                column = stdin.nextInt();
            }

            inputDisk(player, column); //input character in the gameBoard

            printBoard(); //reprints board with updated values

            //Checks to see if a player has won yet and returns the status if true
            if(checkStatus(player)){
                System.out.println("The " + currentPlayer + " player won.");
                return;
            }

            //Changes the player value and the currentPlayer values according to who just played
            player = changePlayer(player);
            if(player == 'R'){
                currentPlayer = "Red";
            }
            else{
                currentPlayer = "Yellow";
            }



        }

    }

}
