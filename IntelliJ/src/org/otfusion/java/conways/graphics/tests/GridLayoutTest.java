package org.otfusion.java.conways.graphics.tests;

import javax.swing.*;
import java.awt.*;

/**
 * Created by
 * User: jms
 * Date: 6/16/13
 * Time: 4:40 PM
 */
public class GridLayoutTest extends JFrame {

    public GridLayoutTest(int rows, int cols) {
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < 100; i++) {
            JButton button = new JButton(Integer.toString(i + 1));
            pane.add(button);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
