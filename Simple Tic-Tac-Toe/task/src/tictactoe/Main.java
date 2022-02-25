package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        char[][] cell = new char[3][3];

        firstInput(cell);

        newCoordinates(cell);
        display(cell);
        // check(cell);

    }

    public static void firstInput(char[][] cell) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter cells: ");
        String cells = scanner.next();



        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j] = cells.charAt(i * cell[0].length + j);
            }
        }

        display(cell);

    }

    public static void display(char[][] cell) {

        for (int i = 0; i < cell.length + 2; i++) {
            for (int j = 0; j < cell[0].length + 2; j++) {
                if (i == 0 || i == cell.length + 1) {
                    System.out.print("---------");
                    break;
                } else if (j == 0 || j == cell[0].length + 1) {
                    System.out.print("| ");
                } else {
                    System.out.print(cell[i-1][j-1] + " ");
                }
            }
            System.out.println();
        }

    }

    public static void newCoordinates(char[][] cells) {


        String[] inputString = new String[2];

        int[] inputs = new int[2];

        boolean takeInput = true;

        do {
            System.out.println("Enter the coordinates: ");

            inputString[0] = checkNum();

            if (inputString[0] == null) {
                continue;
            }

            inputString[1] = checkNum();

            if (inputString[1] == null) {
                continue;
            }


            for (int i = 0; i < 2; i++) {

                inputs[i] = Integer.parseInt(inputString[i]);

                System.out.println(inputs[i]);

                if (inputs[i] > 2 || inputs[i] < 0) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (cells[inputs[0]][inputs[1]] != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    takeInput = false;
                }
            }
        } while (takeInput);

        cells[inputs[0] - 1][inputs[1] - 1] = 'X';


    }

    public static String checkNum() {

        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            return Integer.toString(scanner.nextInt());
        } else {
            System.out.println("You should enter numbers!");
            return null;
        }

    }

    public static void check(char[][] cell) {

        int[] numberOfMoves = {0, 0, 0};

        for (char[] chars : cell) {
            for (char eachChar: chars) {
                if (eachChar == 'X') {
                    numberOfMoves[0]++;
                } else if (eachChar == 'O') {
                    numberOfMoves[1]++;
                } else {
                    numberOfMoves[2]++;
                }
            }
        }

        int difference = numberOfMoves[0] - numberOfMoves[1];

        if (difference > 1 || difference < -1) {
            System.out.println("Impossible");
        } else {
            boolean[] checker = {false, false};

            for (int i = 0; i < 3; i++) {
                if (cell[i][0] == cell[i][1] && cell[i][1] == cell[i][2]) {
                    if (cell[i][0] == 'X') {
                        checker[0] = true;
                    } else if (cell[i][0] == 'O') {
                        checker[1] = true;
                    }
                } else if (cell[0][i] == cell[1][i] && cell[1][i] == cell[2][i]) {
                    if (cell[0][i] == 'X') {
                        checker[0] = true;
                    } else if (cell[0][i] == 'O') {
                        checker[1] = true;
                    }
                }
            }

            if ((cell[0][0] == cell[1][1] && cell[1][1] == cell[2][2]) ||
                    (cell[0][2] == cell[1][1] && cell[1][1] == cell[2][0])) {
                if (cell[1][1] == 'X') {
                    checker[0] = true;
                } else if (cell[1][1] == 'O') {
                    checker[1] = true;
                }
            }

            if (checker[0] && checker[1]) {
                System.out.println("Impossible");
            } else if (checker[0]) {
                System.out.println("X wins");
            } else if (checker[1]) {
                System.out.println("O wins");
            } else {
                if (numberOfMoves[2] == 0) {
                    System.out.println("Draw");
                } else {
                    System.out.println("Game not finished");
                }
            }
        }
    }
}
