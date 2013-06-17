package org.otfusion.java.conways.graphics;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import org.otfusion.java.conways.engine.Engine;
import org.otfusion.java.conways.graphics.controllers.MainFormController;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
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

        // TODO OS X doesnt change JButton background color, so I will try a hack soon.
        try {
            if(System.getProperty("os.name").contains("Mac")) {
                UIManager.setLookAndFeel(new MetalLookAndFeel());
            } else {
                // Use Default (I need a proper Linux & Windows test)
            }
        } catch (Exception e) {}

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

        // Create buttons: start, stop and exit
        this.startButton = new JButton("PlayL3l");
        startButton.setFont(font);
        startButton.setSize(dimensionForButtons);
        startButton.setPreferredSize(dimensionForButtons);

        this.stopButton = new JButton("StopL3L");
        stopButton.setFont(font);
        stopButton.setSize(dimensionForButtons);
        stopButton.setPreferredSize(dimensionForButtons);

        JButton exitButton = new JButton("ExitL3l");
        exitButton.setFont(font);
        exitButton.setSize(dimensionForButtons);
        exitButton.setPreferredSize(dimensionForButtons);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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
        // TODO OS X hack, remember.
        Dimension cellSize = new Dimension(CELL_SIZE, CELL_SIZE);

        JButton button = new JButton();
        button.setPreferredSize(cellSize);
        button.setMinimumSize(cellSize);
        //button.setMaximumSize(cellSize);
        button.setSize(cellSize);
        button.setBackground(Color.WHITE);
        button.setOpaque(true);
        return button;
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
