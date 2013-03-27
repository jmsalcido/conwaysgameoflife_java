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

    // default generations if user is too damn stupid
    private static int DEFAULT_GENERATIONS = 3;
    
    // set the initial board here
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
    
// Debug shit    
//    private static String board = ""
//            + "010\n"
//            + "010\n"
//            + "010\n";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // get the game logic
        Logic logic = Logic.getLogic();
        
        // set the main board
        logic.setStringBoard(board);
        
        // create the input (or it could be read from the command line)
        //if(args[0] != null) {
        //    getNumber(args[0]);
        //}
        Scanner input = new Scanner(System.in);
        
        // ask for the number of generations
        System.out.println("Number of generations: (0,n)");
        int generations = getNumber(input);
        
        // print the first generation BOARD
        System.out.println(board);
        
        // space traveler, get ready to iterate thru the space and time!
        // navigate thru generations
        for(int i = 0; i < generations; i++) {
            
            // print the board
            System.out.println(logic.getNextGenerationBoard());
            
            // if this is not the last generation of the universe
            // prepare them to die or keep them alive.
            if(i != lastGeneration(generations)) {
                // No ncurses or java curses library for you.
                System.out.println("Press any key for next generation");
                input.nextLine();
            } else {
                
                // End of the "game"
                System.out.println("The universe exploded.");
            }
        }
    }
    
    // no magic numbers please
    private static int lastGeneration(int n) {
        return n-1; // n-1 = last one before the end, DUH
    }
    
    // get number from input
    private static int getNumber(Scanner input) {
        String number = input.nextLine();
        return getNumber(number);
    }
    
    // safe get number from string
    private static int getNumber(String number) {
        // number of generatios saved in an integer
        int generations;
        
        // try to get a number from String
        // if it is not possible, welp
        // use the DEFAULT_GENERATIONS constnt.
        try {
            generations = Math.abs(Integer.parseInt(number));
            if(generations <= 0) {
                generations = 1;
            } else {
                // nothing
            }
        } catch (NumberFormatException nfe) {
            generations = DEFAULT_GENERATIONS;
            String out = String.format("\nWrong answer using %d as n\n", generations);
            System.out.println(out);
        }
        
        // return n generations to explore
        return generations;
    }
}
