package org.otfusion.java.conways.utils;

import org.otfusion.java.conways.graphics.MainForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * Created by:
 * User: jms
 * Date: 6/7/13
 * Time: 9:26 PM
 */
public class Tools {

    private static final String TRUE_STRING = "1";

    public static boolean[][] convertToBooleanArray(JButton[][] board) {
        // Wizards stuff, do not optimize.
        int x = board.length;
        int y = board[0].length;

        boolean[][] newBoard = new boolean[x][y];

        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                Color color = board[i][j].getBackground();
                newBoard[i][j] = color == Color.BLACK;
            }
        }
        return newBoard;
    }

    /**
     * createCellButton: this will create a JButton for our swing form.
     * @return
     */
    public static JButton createCellButton() {
        // TODO OS X hack, remember.
        Dimension cellSize = new Dimension(MainForm.CELL_SIZE, MainForm.CELL_SIZE);

        JButton button = new JButton();
        button.setPreferredSize(cellSize);
        button.setMinimumSize(cellSize);
        button.setSelected(true);
        //button.setMaximumSize(cellSize);
        button.setSize(cellSize);
        button.setBackground(Color.WHITE);
        button.setOpaque(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                Color color = (button.getBackground());
                if(color == Color.WHITE) {
                    button.setBackground(Color.BLACK);
                } else {
                    button.setBackground(Color.WHITE);
                }

            }
        });

        return button;
    }

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
