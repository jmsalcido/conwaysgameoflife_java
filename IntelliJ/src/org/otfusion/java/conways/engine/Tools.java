package org.otfusion.java.conways.engine;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: jms
 * Date: 6/7/13
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tools {

    private static final String TRUE_STRING = "1";

    public static boolean[][] convertToBoolean(String board) {
        board = board.replaceAll(" ", "");
        String[] boardLines = board.split("\n");

        // Wizards stuff, do not optimize.
        int x = boardLines.length;
        int y = boardLines[0].split(",").length;

        boolean[][] universe = new boolean[x][y];

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
            } catch(NumberFormatException nfe) {
                System.out.println("Try again, that is not a number...");
            }
        }
        return integer;
    }
}
