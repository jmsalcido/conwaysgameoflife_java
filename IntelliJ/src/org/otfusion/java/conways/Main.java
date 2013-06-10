package org.otfusion.java.conways;

import org.otfusion.java.conways.engine.Engine;
import org.otfusion.java.conways.engine.Tools;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: jms
 * Date: 6/6/13
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        consoleGame();
        // TODO: graphicGame();
    }

    private static void consoleGame() {
        System.out.println("AH! Welcome to the Conways Game of Life Approach in Java (Console):");
        System.out.println("Tell me, the size of the board please:");
        //System.out.println("X - WIDTH: ");
        //int x = readSafeInt(new Scanner(System.in));
        //System.out.println("Y - HEIGHT: ");
        //int y = readSafeInt(new Scanner(System.in));
        Engine engine = Engine.createEngine(10, 10);
        System.out.println("How many generations will be there? (default 5):");
        int generations = Tools.readSafeInt(new Scanner(System.in));
        engine.setGenerations(generations);
        String stringUniverse =
                "0, 1, 0, 0, 0, 0, 0, 0, 0, 0\n" +
                "0, 0, 1, 0 ,0 ,0, 0, 0, 0, 0\n" +
                "1, 1, 1, 0, 0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 1, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 1, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 1, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n";

        boolean[][] booleanUniverse = Tools.convertToBoolean(stringUniverse);
        engine.getUniverse().setUniverse(booleanUniverse);
        engine.init();
    }

}
