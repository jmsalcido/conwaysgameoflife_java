package org.otfusion.java.conways.graphics.controllers;

import org.otfusion.java.conways.engine.Engine;
import org.otfusion.java.conways.engine.Universe;
import org.otfusion.java.conways.graphics.MainForm;
import org.otfusion.java.conways.utils.Tools;

import javax.swing.*;
import java.awt.*;

/**
 * Created by
 * User: jms
 * Date: 6/16/13
 * Time: 10:27 PM
 */
public class MainFormController {

    public static final int STATUS_PLAYING = 0;
    public static final int STATUS_STOPPED = 1;

    private Engine engine;
    private MainForm form;

    private int status;

    public MainFormController(MainForm form, int x, int y) {
        engine = Engine.createEngine(x, y);
        this.form = form;
        this.status = STATUS_STOPPED;
    }

    public void exit() {
        System.exit(0);
    }

    public void start() {
        if(status == STATUS_PLAYING) {
            return;
        } else {
            status = STATUS_PLAYING;
            disableCells();
            form.getStartButton().setEnabled(false);
            form.getStopButton().setEnabled(true);
            boolean[][] cells = Tools.convertToBooleanArray(form.getCellButtons());
            engine.getUniverse().setUniverse(cells);
            engine.init();

        }
    }

    public void stop() {
        if(status == STATUS_STOPPED) {
            return;
        } else {
            status = STATUS_STOPPED;
            enableCells();
            form.getStopButton().setEnabled(false);
            form.getStartButton().setEnabled(true);
        }
    }

    private void setEnabledButtonCells(final boolean enable) {
        int x = engine.getUniverse().getColumns();
        int y = engine.getUniverse().getRows();
        JButton[][] cellButtons = form.getCellButtons();

        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                final JButton button = cellButtons[i][j];
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        button.setEnabled(enable);
                    }
                });
            }
        }
    }

    private void disableCells() {
        setEnabledButtonCells(false);
    }

    private void enableCells() {
        setEnabledButtonCells(true);
    }
}
