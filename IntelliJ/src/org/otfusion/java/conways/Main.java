package org.otfusion.java.conways;

import org.otfusion.java.conways.engine.Engine;
import org.otfusion.java.conways.graphics.MainForm;
import org.otfusion.java.conways.utils.Tools;
import org.otfusion.java.conways.log.Log;

import java.util.Scanner;

/**
 * Created by:
 * User: jms
 * Date: 6/6/13
 * Time: 3:41 PM
 */
public class Main {

    /**
     * start here.
     * @param args
     */
    public static void main(String[] args) {
        Log.v("GAME","New Game Starting...");
        //consoleGame();
        graphicGame();
    }

    /**
     * consoleGame: print and watch a steady universe. I dont know how to create a universe from a seed
     * what should I do?
     */
    private static void consoleGame() {
        // TODO: Create a universe from a seed (like time, or mouse position)
        System.out.println("AH! Welcome to the Conways Game of Life Approach in Java (Console):");
        System.out.println("Tell me, the size of the board please:");
        //System.out.println("X - WIDTH: ");
        //int x = readSafeInt(new Scanner(System.in));
        //System.out.println("Y - HEIGHT: ");
        //int y = readSafeInt(new Scanner(System.in));
        Engine engine = Engine.createEngine(11, 10);
        System.out.println("How many generations will be there? (default 5):");
        int generations = Tools.readSafeInt(new Scanner(System.in));
        engine.setGenerations(generations);
        String stringUniverse =
                "0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0\n" +
                "0, 0, 1, 0 ,0 ,0, 0, 0, 0, 0, 0\n" +
                "1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n";

        boolean[][] booleanUniverse = Tools.convertToBooleanArray(stringUniverse);
        engine.getUniverse().setUniverse(booleanUniverse);
        engine.init();
    }

    private static void graphicGame() {
        // TODO: this, but I will use swing.
        // Always run it on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainForm();
            }
        });
    }

}
