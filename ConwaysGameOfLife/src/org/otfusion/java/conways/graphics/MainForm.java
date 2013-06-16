package org.otfusion.java.conways.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by
 * User: jms
 * Date: 6/11/13
 * Time: 10:30 PM
 */
public class MainForm {

    private static final int CELL_SIZE = 20;

    private JPanel topPanel;
    private JButton startButton;
    private JButton stopButton;

    private JPanel controlPanel;
    private JButton[][] cellButtons;

    public MainForm() {
        JFrame guiFrame = new JFrame("Conways Game of Life");

        // Set Size & Operations over Frame
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //guiFrame.setLocationRelativeTo(null);

        Dimension dimension = new Dimension(800,600);
        guiFrame.setSize(dimension);
        guiFrame.setMinimumSize(dimension);
        //guiFrame.setMaximumSize(dimension);
        guiFrame.setResizable(true);

        // Create GUI
        createGUI(guiFrame);

        // Launch it.
        guiFrame.pack();
        guiFrame.setVisible(true);
    }

    /**
     * createGUI: Create foundation of our game, create buttons and all!
     * @param frame
     */
    private void createGUI(JFrame frame) {
        this.topPanel = createTopPanel(frame);
        this.controlPanel = createControlPanel(frame);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.CENTER);
    }

    private JPanel createTopPanel(JFrame frame) {
        // Default Sizes and all
        Font font = new Font("Helvetica", Font.PLAIN, 16);
        Dimension dimensionForButtons = new Dimension(100,48);

        // Create panel
        JPanel panel = new JPanel();
        panel.setVisible(true);

        // Create buttons
        this.startButton = new JButton("PlayL3l");
        startButton.setFont(font);
        startButton.setSize(dimensionForButtons);
        startButton.setPreferredSize(dimensionForButtons);

        this.stopButton = new JButton("StopL3L");
        stopButton.setFont(font);
        stopButton.setSize(dimensionForButtons);
        stopButton.setPreferredSize(dimensionForButtons);

        panel.add(startButton);
        panel.add(stopButton);

        return panel;
    }

    private JPanel createControlPanel(JFrame frame) {
        int wFrame = frame.getWidth();
        int hFrame = frame.getHeight();

        int hTopPanel = topPanel.getHeight();
        Dimension dimension = new Dimension(wFrame, hFrame-hTopPanel);

        // y = columns
        // x = rows
        int columns = wFrame/CELL_SIZE;
        int rows = hFrame/CELL_SIZE;
        int nCells = rows*columns;

        this.cellButtons = new JButton[rows][columns];

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, columns));
        panel.setSize(dimension);
        panel.setMinimumSize(dimension);
        panel.setVisible(true);

        JButton cell = null;

        for(int i = 0; i < nCells ; i++) {
            // this can be in a nested for or calculating the x and y way.
            cell = createCellButton();
            int x = i/columns;
            int y = i-(columns*x);
            // Debug shit.
            //System.out.println(String.format("cellButtons[%d][%d] = %d",x,y, i));
            cellButtons[x][y] = cell;
            panel.add(cell);
        }
        return panel;
    }

    private JButton createCellButton() {
        Dimension cellSize = new Dimension(CELL_SIZE, CELL_SIZE);

        JButton button = new JButton();
        button.setPreferredSize(cellSize);
        button.setMinimumSize(cellSize);
        //button.setMaximumSize(cellSize);
        button.setSize(cellSize);
        button.setBackground(Color.WHITE);
        return button;
    }
}
