package org.otfusion.java.conways.utils;

import java.util.Scanner;

/**
 * Created by:
 * User: jms
 * Date: 6/7/13
 * Time: 9:26 PM
 */
public class Tools {

    private static final String TRUE_STRING = "1";

    /**
     * convertToBooleanArray: Convert a String board to a boolean[][]
     * @param board
     * @return the universe: o.o!
     */
    public static boolean[][] convertToBooleanArray(String board) {
        // remove the trash.
        board = board.replaceAll(" ", "");
        String[] boardLines = board.split("\n");

        // Wizards stuff, do not optimize.
        int x = boardLines.length;
        int y = boardLines[0].split(",").length;

        // create the new universe
        boolean[][] universe = new boolean[x][y];

        // set the entire universe... yeah.
        for(int i = 0; i < x; i++) {
            String[] y_columns = boardLines[i].split(",");
            for(int j = 0; j < y; j++) {
                universe[i][j] = y_columns[j].equals(TRUE_STRING);
            }
        }

        return universe;
    }

    /**
     * readSafeInt: welp, sometimes the user is like a monkey :)
     * @param scanner
     * @return
     */
    public static Integer readSafeInt(Scanner scanner) {
        boolean safe = false;
        Integer integer = -1;
        while(!safe) {
            try {
                integer = scanner.nextInt();
                safe = true;
            } catch(Exception nfe) {
                System.out.println("Try again, that is not a number...");
            }
        }
        return integer;
    }

    /**
     * print: I hate to write System.out.println every time... even Tools.print is better.
     * @param msg
     */
    public static void print(String msg) {
        System.out.println(msg);
    }
}
