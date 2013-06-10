package org.otfusion.java.conways.engine;

/**
 * Created with IntelliJ IDEA.
 * User: jms
 * Date: 6/7/13
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Cell {

    private boolean life;
    private int neighbors;

    public Cell(boolean life) {
        this.life = life;
    }

    public boolean getState() {
        return life;
    }

    public void setNeighbors(int n) {
        this.neighbors = n;
    }

    public int getNeighbors() {
        return this.neighbors;
    }
}
