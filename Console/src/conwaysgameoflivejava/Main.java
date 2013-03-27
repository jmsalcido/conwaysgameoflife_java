// Conway's Game of Life
// Jose Miguel Salcido Aguilar
// 26 March 2013
//
// Rules of the Conway's Game of Life:
//    - Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
//    - Any live cell with more than three live neighbours dies, as if by overcrowding.
//    - Any live cell with two or three live neighbours lives on to the next generation.
//    - Any dead cell with exactly three live neighbours becomes a live cell.
package conwaysgameoflivejava;

import conwaysgameoflivejava.game.Logic;
import java.util.Scanner;

/**
 *
 * @author jms
 */
public class Main {

    private static String board = ""
            + "001010000000000000000000010000\n"
            + "010110000000000000000000101000\n"
            + "001110000000000000000000010000\n"
            + "000000000011100000000000000000\n"
            + "000000000000000000000000000000\n"
            + "000110000000000000000000000000\n"
            + "000110000000000000000000000000\n"
            + "000000000000000000000110000000\n"
            + "000000000000000000000110000000\n"
            + "000000000000000000000000000000\n";
    
//    private static String board = ""
//            + "010\n"
//            + "010\n"
//            + "010\n";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logic logic = Logic.getLogic();
        logic.setStringBoard(board);
        Scanner input = new Scanner(System.in);
        System.out.println("Number of generations: (0,n)");
        int generations = getNumber(input);
        //int generations = 3;
        for(int i = 0; i < generations; i++) {
            System.out.println(logic.getNextGenerationBoard());
            if(i != lastGeneration(generations)) {
                System.out.println("Press any key for next generation");
                input.nextLine();
            } else {
                // END
            }
        }
    }
    
    private static int lastGeneration(int n) {
        return n-1;
    }
    
    private static int getNumber(Scanner input) {
        int generations;
        try {
            generations = Math.abs(Integer.parseInt(input.nextLine()));
            if(generations <= 0) {
                generations = 1;
            } else {
                // nothing
            }
        } catch (NumberFormatException nfe) {
            generations = 3;
            String out = String.format("\nWrong answer using %d as n\n", generations);
            System.out.println(out);
        }
        return generations;
    }
}
