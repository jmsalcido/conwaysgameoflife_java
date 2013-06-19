package org.otfusion.java.conways.graphics;

import org.otfusion.java.conways.graphics.controllers.MainFormController;
import org.otfusion.java.conways.utils.Tools;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by
 * User: jms
 * Date: 6/11/13
 * Time: 10:30 PM
 */
public class MainForm {

    public static final int CELL_SIZE = 20;

    private JPanel topPanel;
    private JButton startButton;
    private JButton stopButton;

    private JPanel controlPanel;
    private JButton[][] cellButtons;

    private MainFormController control;

    public MainForm() {
        JFrame guiFrame = new JFrame("Conway's Game of Life");

        setLookAndFeel();

        // Set Size & Operations over Frame
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dimension = new Dimension(800,600);
        guiFrame.setSize(dimension);
        guiFrame.setMinimumSize(dimension);
        //guiFrame.setMaximumSize(dimension);
        guiFrame.setResizable(true);

        // Create GUI & Control
        createControlAndGUI(guiFrame);

        // Launch it.
        guiFrame.pack();
        guiFrame.setVisible(true);
    }

    private void setLookAndFeel() {
        // TODO OS X doesnt change JButton background color, so I will try a hack soon.
        try {
            if(System.getProperty("os.name").contains("Mac")) {
                UIManager.setLookAndFeel(new MetalLookAndFeel());
            } else {
                // Use Default (I need a proper Linux & Windows test)
            }
        } catch (Exception e) {}
    }

    /**
     * createGUI: Create foundation of our game, create buttons and all!
     * @param frame
     */
    private void createControlAndGUI(JFrame frame) {
        // y = columns
        // x = rows
        int columns = frame.getWidth()/CELL_SIZE;
        int rows = frame.getHeight()/CELL_SIZE;

        // Create control
        // TODO debug x&y son.
        this.control = new MainFormController(this, rows, columns);

        this.topPanel = createTopPanel();
        this.controlPanel = createControlPanel(frame);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        // Default Sizes and all
        Font font = new Font("Helvetica", Font.PLAIN, 16);
        Dimension dimensionForButtons = new Dimension(100,48);

        // Create panel
        JPanel panel = new JPanel();
        panel.setVisible(true);

        // Create buttons: start, stop and exit
        // and set their default values.
        this.startButton = new JButton("PlayL3l");
        startButton.setFont(font);
        startButton.setSize(dimensionForButtons);
        startButton.setPreferredSize(dimensionForButtons);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.start();
            }
        });

        this.stopButton = new JButton("StopL3L");
        stopButton.setEnabled(false);
        stopButton.setFont(font);
        stopButton.setSize(dimensionForButtons);
        stopButton.setPreferredSize(dimensionForButtons);

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.stop();
            }
        });

        JButton exitButton = new JButton("ExitL3l");
        exitButton.setFont(font);
        exitButton.setSize(dimensionForButtons);
        exitButton.setPreferredSize(dimensionForButtons);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.exit();
            }
        });

        panel.add(startButton);
        panel.add(stopButton);
        panel.add(exitButton);

        return panel;
    }

    private JPanel createControlPanel(JFrame frame) {
        int wFrame = frame.getWidth();
        int hFrame = frame.getHeight();

        // TODO topPanel has no height
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

        // wut
        JButton cell;
        // Create the JButtons for each cell.
        for(int i = 0; i < nCells ; i++) {
            // this can be in a nested for or calculating the x and y way.
            cell = Tools.createCellButton();
            int x = i/columns;
            int y = i-(columns*x);
            // Debug shit.
            //System.out.println(String.format("cellButtons[%d][%d] = %d",x,y, i));
            cellButtons[x][y] = cell;
            panel.add(cell);
        }
        return panel;
    }

    // gets and sets.
    // ===================================================
    public JPanel getTopPanel() {
        return this.topPanel;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public JButton getStartButton() {
        return this.startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public JButton getStopButton() {
        return this.stopButton;
    }

    public void setStopButton(JButton stopButton) {
        this.stopButton = stopButton;
    }

    public JPanel getControlPanel() {
        return this.controlPanel;
    }

    public void setControlPanel(JPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public JButton[][] getCellButtons() {
        return this.cellButtons;
    }

    public void setCellButtons(JButton[][] cellButtons) {
        this.cellButtons = cellButtons;
    }

}
