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
 *
 * @author jms
 */
public class Logic {
    
    private static int X;
    private static int Y;
    
    private static Logic mLogic;
    private String mStringBoard;
    private boolean[][] mBoard;
    
    private Logic() {
    }
    
    private Logic(String board) {
        this.mStringBoard = board;
        Logic.X = getX(mStringBoard);
        Logic.Y = getY(mStringBoard);
        this.mBoard = convertStringToBooleanBoard(mStringBoard);
    }
    
    private int getX(String board) {
        StringBuilder sb = new StringBuilder(board);
        String s = sb.substring(0, sb.indexOf("\n"));
        return s.length();
    }
    
    private int getY(String board) {
        String s = board.replace("0", "");
        s = s.replace("1", "");
        s = s.replace("\n", "0 ");
        String[] arr = s.split(" ");
        return arr.length;
    }
    
    public void setBooleanBoard(boolean[][] board) {
        this.mBoard = board;
        this.mStringBoard = convertBooleanToStringBoard(mBoard);
        Logic.X = getX(mStringBoard);
        Logic.Y = getY(mStringBoard);
    }
    
    public boolean[][] getBooleanBoard() {
        return this.mBoard;
    }
    
    public void setStringBoard(String board) {
        this.mStringBoard = board;
        Logic.X = getX(mStringBoard);
        Logic.Y = getY(mStringBoard);
        this.mBoard = convertStringToBooleanBoard(mStringBoard);
    }
    
    public String getStringBoard() {
        return this.mStringBoard;
    }
    
    public static Logic getLogic() {
        if(mLogic == null) {
            mLogic = new Logic();
        } else {
            // DAMN
        }
        return mLogic;
    }
    
    public static Logic getLogic(String board) {
        if(mLogic == null) {
            mLogic = new Logic(board);
        } else {
            // DAMN
        }
        return mLogic;
    }
    
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
    
    public String getNextGenerationBoard() {
        mBoard = calculateNextGeneration(mBoard);
        this.mStringBoard = convertBooleanToStringBoard(mBoard);
        return getStringBoard();
    }
    
    private boolean[][] calculateNextGeneration(boolean[][] board) {
        boolean[][] nextBoard = new boolean[Y][X];
        for(int y = 0; y < board.length; y++) {
            for(int x = 0; x < board[0].length; x++) {
                int neighbours = numberOfLiveNeighbours(board, x, y);
                nextBoard[y][x] = applyRules(board, x, y, neighbours);
            }
        }
        return nextBoard;
    }
    
    private boolean applyRules(boolean[][] board, int x, int y, int n) {
        boolean cell = board[y][x];
        if(cell && (n < 2 || n > 3)) {
            cell = false;
        }
        if(cell && (n == 2 || n == 3)) {
            cell = true;
        }
        if(!cell && n == 3) {
            cell = true;
        }
        return cell;
    }
    
    private int numberOfLiveNeighbours(boolean[][] board, int x, int y) {
        int n = 0;
        boolean cell;
        for(int i = y-1; i < y+2; i++) {
            if(i >= Logic.Y || i < 0) {
                continue;
            } else {/*nothing happens*/}
            for(int j = x-1; j < x+2; j++) {
                if(j >= Logic.X || j < 0 || (j == x && i == y)) {
                    continue;
                } else {/*nothing happens*/}
                cell = board[i][j];
                if(cell) {
                    n++;
                }
            }
        }
        return n;
    }
}
