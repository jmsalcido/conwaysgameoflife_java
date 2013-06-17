package org.otfusion.java.conways.graphics.controllers;

import org.otfusion.java.conways.engine.Engine;
import org.otfusion.java.conways.graphics.MainForm;

/**
 * Created by
 * User: jms
 * Date: 6/16/13
 * Time: 10:27 PM
 */
public class MainFormController {

    private Engine engine;
    private MainForm form;

    public MainFormController(MainForm form, int x, int y) {
        engine = Engine.createEngine(x, y);
        this.form = form;
    }
}
