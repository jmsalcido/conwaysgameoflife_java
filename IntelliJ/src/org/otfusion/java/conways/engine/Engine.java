package org.otfusion.java.conways.engine;

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

    private Engine(int x, int y) {
        this.universe = new Universe(x,y);
    }

    public void init() {
        for(int i = 0; i < generations; i ++) {
            printUniverse();
            universe.calculateNextGeneration();
        }
    }

    private void printUniverse() {
        String stringUniverse = null;
        stringUniverse = universe.stringify();
        System.out.println(stringUniverse);
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

    public Universe getUniverse() {
        return this.universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public void setGenerations(int generations) {
        if(generations <= 0) {
            this.generations = 5;
        } else {
            this.generations = generations;
        }
    }

    public int getGenerations() {
        return this.generations;
    }
}
