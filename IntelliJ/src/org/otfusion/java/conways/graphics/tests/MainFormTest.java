package org.otfusion.java.conways.graphics.tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by
 * User: jms
 * Date: 6/16/13
 * Time: 3:57 PM
 */
public class MainFormTest {

    public MainFormTest() {
        JFrame guiFrame = new JFrame();

        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("GUI Test");
        guiFrame.setSize(300, 250);
        guiFrame.setMinimumSize(guiFrame.getSize());
        guiFrame.setLocationRelativeTo(null);

        String[] fruitOptions = {"Apple", "Apricot", "Banana",
                 "Cherry", "Date", "Kiwi", "Orange", "Pear", "Strawberry"};

        String[] vegOptions = {"Asparagus", "Beans", "Broccoli", "Cabbage",
                 "Carro", "Celery", "Cucumber", "Leek", "Mushroom",
                 "Pepper", "Radish", "Shallot", "Spinach", "Swede",
                 "Turnip"};

        final JPanel comboPanel = new JPanel();
        JLabel comboLabel = new JLabel("Fruits");
        JComboBox fruits = new JComboBox(fruitOptions);

        comboPanel.add(comboLabel);
        comboPanel.add(fruits);

        final JPanel listPanel = new JPanel();
        listPanel.setVisible(false);
        JLabel listLabel = new JLabel("Vegetables");
        JList vegs = new JList(vegOptions);
        vegs.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        listPanel.add(listLabel);
        listPanel.add(vegs);

        JButton vegFruitButton = new JButton("Fruit or Veg");

        vegFruitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listPanel.setVisible(!listPanel.isVisible());
                comboPanel.setVisible(!comboPanel.isVisible());
            }
        });

        guiFrame.add(comboPanel, BorderLayout.NORTH);
        guiFrame.add(listPanel, BorderLayout.CENTER);
        guiFrame.add(vegFruitButton, BorderLayout.SOUTH);

        guiFrame.setVisible(true);
    }
}
