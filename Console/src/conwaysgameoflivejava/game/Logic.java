// Conway's Game of Life
// Jose Miguel Salcido Aguilar
// 26 March 2013
//
// Rules of the Conway's Game of Life:
//    - Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
//    - Any live cell with more than three live neighbours dies, as if by overcrowding.
//    - Any live cell with two or three live neighbours lives on to the next generation.
//    - Any dead cell with exactly three live neighbours becomes a live cell.
package conwaysgameoflivejava.game;

/**
 * Logic class, before you look at the code: the boolean universe
 * boolean[][] board has the X and Y flipped because for me
 * [rows][columns] and X = columns and Y = rows,
 * okay?, okay.
 * @author jms
 */
public class Logic {
    
    // SIZE OF THE BOARD
    private static int X;
    private static int Y;
    
    // members of the class
    private static Logic mLogic;    // singleton instance (no new logics allowed)
    
    /* --------- I think I will remove these --------- */
    private String mStringBoard;    // first board saved... i dont know why
    private boolean[][] mBoard;     // this can be removed too
    
    private Logic() {
    }
    
    /**
     * Load the board and calculate X and Y.
     * @param board 
     */
    private Logic(String board) {
        this.mStringBoard = board;
        Logic.X = getX(mStringBoard);
        Logic.Y = getY(mStringBoard);
        this.mBoard = convertStringToBooleanBoard(mStringBoard);
    }
    
    /**
     * Calculate X size from board (string)
     * @param board
     * @return 
     */
    private int getX(String board) {
        StringBuilder sb = new StringBuilder(board);
        String s = sb.substring(0, sb.indexOf("\n"));
        return s.length();
    }
    
    /**
     * Calculate Y size from board (string)
     * @param board
     * @return 
     */
    private int getY(String board) {
        String s = board.replace("0", "");
        s = s.replace("1", "");
        s = s.replace("\n", "0 ");
        String[] arr = s.split(" ");
        return arr.length;
    }
    
    /**
     * Set boolean[][] Board and calculate X and Y
     * @param board 
     */
    public void setBooleanBoard(boolean[][] board) {
        this.mBoard = board;
        this.mStringBoard = convertBooleanToStringBoard(mBoard);
        Logic.X = getX(mStringBoard);
        Logic.Y = getY(mStringBoard);
    }
    
    /**
     * Get.
     * @return 
     */
    public boolean[][] getBooleanBoard() {
        return this.mBoard;
    }
    
    /**
     * Set String Board and calculate X and Y
     * @param board 
     */
    public void setStringBoard(String board) {
        this.mStringBoard = board;
        Logic.X = getX(mStringBoard);
        Logic.Y = getY(mStringBoard);
        this.mBoard = convertStringToBooleanBoard(mStringBoard);
    }
    
    /**
     * Get.
     * @return 
     */
    public String getStringBoard() {
        return this.mStringBoard;
    }
    
    /**
     * Singleton to create a new Logic class
     * @return 
     */
    public static Logic getLogic() {
        if(mLogic == null) {
            mLogic = new Logic();
        } else {
            // DAMN
        }
        return mLogic;
    }
    
    /**
     * Singleton to create a new Logic class
     * @param board
     * @return 
     */
    public static Logic getLogic(String board) {
        if(mLogic == null) {
            mLogic = new Logic(board);
        } else {
            // DAMN
        }
        return mLogic;
    }
    
    /**
     * Convert String Board to boolean[][]
     * @param stringBoard
     * @return 
     */
    private boolean[][] convertStringToBooleanBoard(String stringBoard) {
        // create the array (fixed for the moment)
        boolean[][] board = new boolean[Y][X];
        
        // create a StringBuilder that will get every char from our board
        StringBuilder sb = new StringBuilder(stringBoard);
        
        // navigate thru y
        for(int y = 0; y < board.length; y++) {
            
            // navigate thru x
            for(int x = 0; x < board[0].length; x++) {
                
                // obtain charAt x,y position
                // in the string board y = (x max lenght + 1 ) * y
                // 1 because of the end of line char at the end of the stringBoard
                char charAt = sb.charAt(((board[0].length+1) * y) + x);
                
                // if we encounter the end of line, fuck it.
                if(charAt == '\n') {
                    
                    continue;
                } else {
                    
                    // check it the character is 1, for true else is false.
                    board[y][x] = charAt == '1';
                    
                    // debug info
                    //System.out.println(String.format("i = %d, j = %d, charAt = %s", i, j, charAt));
                }
            }
        }
        
        // return our board
        return board;
    }
    
    /**
     * Convert boolean[][] board to String
     * @param booleanBoard
     * @return 
     */
    private String convertBooleanToStringBoard(boolean[][] booleanBoard) {
        // create the new object that will build our string board
        StringBuilder sb = new StringBuilder();
        
        // navigate thru y
        for(int y = 0; y < booleanBoard.length; y++) {
            
            // navigate thru x
            for(int x = 0; x < booleanBoard[0].length; x++) {
                
                // check if (x,abs(-y)) is true or false
                // 1 = true
                // 0 = false
                char charAt = booleanBoard[y][x] ? '1' : '0';
                sb.append(charAt);
            }
            
            // always append the end of line char
            sb.append('\n');
        }
        
        // return our string board
        return sb.toString();
    }
    
    /**
     * Get the next generation String Board
     * @return 
     */
    public String getNextGenerationBoard() {
        // Oh I remember now why I had mBoard
        mBoard = calculateNextGeneration(mBoard);
        
        // Yeh. This probably should be removed.
        this.mStringBoard = convertBooleanToStringBoard(mBoard);
        return getStringBoard();
    }
    
    /**
     * Calculate the Next Generation in boolean[][] board
     * @param board
     * @return 
     */
    private boolean[][] calculateNextGeneration(boolean[][] board) {
        // Create a pre-bigbang universe with X and Y
        boolean[][] nextBoard = new boolean[Y][X];
        
        // navigate thru space
        
        // iterate thru Y
        for(int y = 0; y < board.length; y++) {
            // itarate thru X
            for(int x = 0; x < board[0].length; x++) {
                // get the number of aliens
                int neighbours = numberOfLiveNeighbours(board, x, y);
                
                // apply the rules of Conway's game of life.
                nextBoard[y][x] = applyRules(board, x, y, neighbours);
            }
        }
        
        // return new board
        return nextBoard;
    }
    
    /**
     * Apply the rules of the universe, get ready to kill some particles
     * @param board the board in boolean[][] form
     * @param x column
     * @param y row
     * @param n number of neighbours
     * @return 
     */
    private boolean applyRules(boolean[][] board, int x, int y, int n) {
        // obtain our "host" cell
        boolean cell = board[y][x];
        
        // 2 Rules here:
        //
        // 1. Any live cell with fewer than two live neighbours dies
        //  as if caused by underpopulation.
        // 2. Any live cell with more than three live neighbours dies,
        //  as if by overcrowding.
        if(cell && (n < 2 || n > 3)) {
            cell = false;
        }
        
        // 1 Rule here:
        //
        // 1. Any live cell with two or three live neighbours lives
        //  on to the next generation.
        if(cell && (n == 2 || n == 3)) {
            cell = true;
        }
        
        // Dead Rule here:
        // Any dead cell with exactly three live neighbours becomes a live cell.
        if(!cell && n == 3) {
            cell = true;
        }
        
        // return cell status
        return cell;
    }
    
    /**
     * Obtain the number of live neighbours
     * @param board the board in boolean[][] form
     * @param x
     * @param y
     * @return 
     */
    private int numberOfLiveNeighbours(boolean[][] board, int x, int y) {
        
        // how does this work in my stupid (jms) mind:
        //
        // each cell has 8 neighbours
        // [1] [2] [3]
        // [8] [C] [4]
        // [7] [6] [5]
        //
        // C = Cell = (X,Y) or board[Y][X] (rows and columns)
        //
        // So basically, we need to search thru (Y-1,X-1) to (Y+1, X+1)
        
        int n = 0;
        boolean cell;
        
        // iterate from y-1 to y+2
        // ex: y = 0: -1,0,1
        for(int i = y-1; i < y+2; i++) {
            
            // if i is below 0 or greather than Y continue.
            // in the example: i = -1, board[-1][X] does not exist.
            if(i >= Logic.Y || i < 0) {
                continue;
            } else {/*nothing happens*/}
            
            // iterate from x-1 to x+2
            // ex: x = 0: -1,0,1
            for(int j = x-1; j < x+2; j++) {
                // if j is below 0 or greathen than Y or i = y and j = x
                // continue (x,y) doesnt need to be calculated
                if(j >= Logic.X || j < 0 || (j == x && i == y)) {
                    continue;
                } else {/*nothing happens*/}
                
                // check if that cell is alive
                // if yes, add 1 to n if no - fuck you.
                cell = board[i][j];
                if(cell) {
                    n++;
                }
            }
        }
        
        // return the number of cells alive around (x,y)
        return n;
    }
}
