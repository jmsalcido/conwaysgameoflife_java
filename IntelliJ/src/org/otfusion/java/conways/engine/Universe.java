package org.otfusion.java.conways.engine;

import org.otfusion.java.conways.log.Log;

/**
 * Created with IntelliJ IDEA.
 * User: jms
 * Date: 6/6/13
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Universe {

    public static final int OPTION_NEIGHBORS = 0;
    public static final int OPTION_STRING = 1;
    public static final int OPTION_CELL = 2;

    private Cell[][] universe;
    private int rows;
    private int columns;

    /**
     * Universe: create a new universe
     * @param x width
     * @param y height
     */
    public Universe(int x, int y) {
        universe = new Cell[x][y];
        this.columns = x;
        this.rows = y;
    }

    /**
     * setUniverse: set a boolean 2d array as our universe
     * @param universe
     */
    public void setUniverse(boolean[][] universe) {
        int x = universe.length;
        int y = universe[0].length;

        this.universe = new Cell[x][y];

        Cell cell = null;
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                cell = new Cell(universe[i][j]);
                this.universe[i][j] = cell;
            }
        }
    }

    /**
     * stringify: obtain the string universe
     * @return universe in a string (curious)
     */
    public String stringify() {
        return (String) readUniverse(OPTION_STRING);
    }

    /**
     * readUniverse: I dont want to write 3-4 times this "for".
     * @param option
     * @return
     */
    private Object readUniverse(int option) {
        int x = universe.length;
        int y = universe[0].length;

        StringBuilder sb = null;
        String string = null;
        Cell cell = null;
        Cell[][] nextUniverse = new Cell[x][y];

        // Lets verify if the universe will be returned in String.
        if(option == OPTION_STRING) {
            sb = new StringBuilder();
        }

        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                switch(option) {
                    case OPTION_STRING:
                        sb.append(buildString(universe[i][j]));
                        sb.append(", ");
                        break;
                    case OPTION_CELL:
                        cell = universe[i][j];
                        int neighbors = calculateNumberOfNeighbors(i, j);
                        cell.setNeighbors(neighbors);
                        cell = applyRules(cell);
                        nextUniverse[i][j] = cell;
                        break;
                    default:
                        break;
                }
            }
            if(option == OPTION_STRING) {
                sb.delete(sb.length()-(", ".length()), sb.length());
                sb.append("\n");
            }
        }

        switch(option) {
            case OPTION_STRING:
                return sb.toString();
            case OPTION_CELL:
                return nextUniverse;
            default:
                return null;
        }
    }

    /**
     * buildString: set 1 for true and 0 for false
     * @param cell
     * @return
     */
    private String buildString(Cell cell) {
        if(cell.getState()) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * calculateNextGeneration: just... calculate it.
     */
    public void calculateNextGeneration() {
        Log.v("UNIVERSE","Calculating next generation...");
        this.universe = (Cell[][])this.readUniverse(OPTION_CELL);
    }

    /**
     * calculateNumberOfNeighbors: I think that the correct word is "neighbours" but it is okay.
     * calculate the number of "neighbours" looking at:
     *
     * [x][x][x]
     * [x][c][x]
     * [x][x][x]
     *
     * c = our current cell
     *
     * you should look at: ((x-1),(y-1)) to ((x+1), (y+1));
     *
     * if x|y < 0 and x|y > universe.lenght ... please die.
     *
     * @param x current x position
     * @param y current y position
     * @return number of "neighbours"
     */
    private int calculateNumberOfNeighbors(int x, int y) {
        int n = 0;
        int xUniverse = universe.length;
        int yUniverse = universe[0].length;
        int times = 2;
        for(int i = x-1; i < x + times; i++) {
            if(i < 0 || i >= xUniverse) {
                continue;
            } else {}
            for(int j = y-1; j < y + times; j++) {
                if(j < 0 || j >= yUniverse || (i == x && j == y)) {
                    continue;
                } else {}

                if(universe[i][j].getState()) {
                    n++;
                }

            }
        }
        return n;
    }

    /**
     * applyRules: apply the damn rules to a cell.
     * @param cell
     * @return
     */
    private Cell applyRules(Cell cell) {
        cell = applyRule1(cell);
        cell = applyRule2(cell);
        cell = applyRule3(cell);
        cell = applyRule4(cell);
        return cell;
    }

    /**
     * applyRule1: Any live cell with fewer than two live neighbours dies as if caused by underpopulation
     * @param cell
     * @return
     */
    private Cell applyRule1(Cell cell) {
        // Any live cell with fewer than two live neighbours dies as if caused by underpopulation
        if(cell.getState() && (cell.getNeighbors() < 2)) {
            return new Cell(false);
        } else {
            return cell;
        }
    }

    /**
     * applyRule2: Any live cell with fewer than two live neighbours dies as if caused by underpopulation
     * @param cell
     * @return
     */
    private Cell applyRule2(Cell cell) {
        // Any live cell with fewer than two live neighbours dies as if caused by underpopulation
        if(cell.getState() && (cell.getNeighbors() > 3)) {
            return new Cell(false);
        } else {
            return cell;
        }
    }

    /**
     * applyRule3: Any live cell with two or three live neighbours live on to the next generation
     * @param cell
     * @return
     */
    private Cell applyRule3(Cell cell) {
        // Any live cell with two or three live neighbours live on to the next generation
        if(cell.getState() && ((cell.getNeighbors() == 2) || (cell.getNeighbors() == 3))) {
            return new Cell(true);
        } else {
            return cell;
        }
    }

    /**
     * applyRule4: Any dead cell with exactly three live neighbours becomes a live cell
     * @param cell
     * @return
     */
    private Cell applyRule4(Cell cell) {
        // Any dead cell with exactly three live neighbours becomes a live cell
        if((cell.getState() == false) && (cell.getNeighbors() == 3)) {
            return new Cell(true);
        } else {
            return cell;
        }
    }

    public int getRows() {
        return this.rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
