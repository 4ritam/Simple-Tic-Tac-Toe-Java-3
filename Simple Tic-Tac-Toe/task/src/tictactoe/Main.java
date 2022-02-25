package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        char[][] cell = new char[3][3];
        boolean isPlaying = true;
        int turn = 1;

        firstInput(cell);

        while (isPlaying) {
            if(turn % 2 == 1) {
                newCoordinates(cell, 'X');
            } else {
                newCoordinates(cell, 'O');
            }
            if (check(cell)) {
                isPlaying = false;
            }
            turn++;
        }

    }

    public static void firstInput(char[][] cell) {

        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j] = '_';
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
                    if (cell[i-1][j-1] == '_'){
                        System.out.print("  ");
                    } else {
                        System.out.print(cell[i - 1][j - 1] + " ");
                    }
                }
            }
            System.out.println();
        }

    }

    public static void newCoordinates(char[][] cells, char x) {

        Scanner scanner = new Scanner(System.in);

        String[] inputString = new String[2];

        int[] inputs = new int[2];

        boolean takeInput = true;

        boolean toCheck = false;

        do {

            System.out.print("Enter the coordinates: ");

            inputString[0] = scanner.next();

            if (!checkNum(inputString[0])) {
                continue;
            }

            inputString[1] = scanner.next();

            if (!checkNum(inputString[1])) {
                continue;
            }


            for (int i = 0; i < 2; i++) {

                toCheck = false;

                inputs[i] = Integer.parseInt(inputString[i]);

                if (inputs[i] > 3 || inputs[i] < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    break;
                }
                toCheck = true;
            }

            if (toCheck) {
                if (cells[inputs[0] - 1][inputs[1] - 1] != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    takeInput = false;
                }
            }
        } while (takeInput);

        cells[inputs[0] - 1][inputs[1] - 1] = x;

        display(cells);

    }

    public static boolean checkNum(String s) {

        try {
            int x = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        }

    }

    public static boolean check(char[][] cell) {

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
            return true;
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
                return true;
            } else if (checker[0]) {
                System.out.println("X wins");
                return true;
            } else if (checker[1]) {
                System.out.println("O wins");
                return true;
            } else {
                if (numberOfMoves[2] == 0) {
                    System.out.println("Draw");
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
