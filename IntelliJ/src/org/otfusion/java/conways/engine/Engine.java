package org.otfusion.java.conways.engine;

import org.otfusion.java.conways.graphics.MainForm;
import org.otfusion.java.conways.log.Log;

/**
 * This class will have all the powers to print in console and graphic world!
 * User: jms
 * Date: 6/6/13
 * Time: 3:44 PM
 */
public class Engine {

    private static Engine engine;

    private Universe universe;
    private int generations;
    private boolean status;
    private MainForm form;

    /**
     * You cant create a new engine, Singleton master race.
     * @param x
     * @param y
     */
    private Engine(int x, int y) {
        this.universe = new Universe(x,y);
        this.generations = 5;
    }

    private Engine(MainForm form, int x, int y) {
        this.form = form;
        this.universe = new Universe(x,y);
        this.generations = -1;
        this.status = false;
    }

    /**
     * init: init the game song.
     */
    public void init() {
        if(this.form == null && this.generations != -1) {
            Log.v("ENGINE",String.format("Number of generations: %d", this.generations));
            for(int i = 0; i < generations; i ++) {
                printUniverse();
                universe.calculateNextGeneration();
            }
        } else {
            // TODO infinite play here.
            generations = 5;
            for(int i = 0; i < generations; i ++) {
                //universe.calculateNextGeneration();
                //updateUI();
            }
        }
    }

    /**
     * printUniverse: seriously, just pass it thru System.out.
     */
    private void printUniverse() {
        String stringUniverse = null;
        stringUniverse = universe.stringify();
        System.out.println(stringUniverse);
    }

    private void updateUI() {

    }

    /**
     * createEngine: create a singleton instance of our Engine.
     * @param x width of the universe
     * @param y height of the universe
     * @return Engine
     */
    public static Engine createEngine(int x, int y) {
        // Singleton
        if(engine == null) {
            engine = new Engine(x,y);
        } else {
            // Nothing, return the Engine static member
        }
        return engine;
    }

    /**
     * createEngine: create a singleton instance of our Engine.
     * @param form our view
     * @param x width of the universe
     * @param y height of the universe
     * @return Engine
     */
    public static Engine createEngine(MainForm form, int x, int y) {
        // Singleton
        if(engine == null) {
            engine = new Engine(form, x,y);
        } else {
            // Nothing, return the Engine static member
        }
        return engine;
    }

    // gets and sets.
    // ===================================================

    /**
     * getUni... nah im not documenting these.
     * @return
     */
    public Universe getUniverse() {
        return this.universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public void setGenerations(int generations) {
        if(generations <= 5) {
            this.generations = 5;
        } else {
            this.generations = generations;
        }
    }

    public int getGenerations() {
        return this.generations;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
