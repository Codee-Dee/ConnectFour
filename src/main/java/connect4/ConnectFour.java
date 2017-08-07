package connect4;

/**
 * This class provides implements a game board for Connect Four.
 * In this game, the RED player always plays the first token.
 * A Connect Four game board has 6 rows and 7 columns:
 * 
 *  | | | | | | | |
 *  | | | | | | | |
 *  | | | | | | | |
 *  | | | |B| | | |
 *  |B| |B|R| | | |
 *  |R|B|B|R| |R|R|
 *  ---------------
 * 
 * This diagram also illustrates how the board should be printed
 * to the screen (via the toString method).
 * 
 * The first player (RED or BLACK) to get four tokens in a row,
 * either horizontally, vertically, or diagonally, wins the game.
 * It is possible for neither player to win (a TIE) if the board
 * is filled with no winning condition. If the game is still
 * ongoing, the result should be NONE.
 */
 
public class ConnectFour {

    public enum Location {EMPTY, RED, BLACK};
    public enum Result {NONE, TIE, BLACKWIN, REDWIN};
    protected Location[][] board;
    protected boolean redTurn;
    protected boolean blackTurn;
    
    /**
     * This is a convenience method that you might find useful.
     * It returns the specified column's entries as a string.
     * For instance, suppose that you had the following board:
     * 
     * | | | | | | | |
     * | | | | | | | |
     * | | | | | | | |
     * | |B| | |R| | |
     * | |B|R| |R|R|B|
     * |B|R|B| |R|B|R|
     * ---------------
     * 
     * Calling this function on column 1 would return "RBB". On
     * column 5 it would return "BR". (It starts from the "bottom" 
     * of the column.)
     * 
     * @param column the column to convert
     * @return a string representing that column's contents
     */
    protected String getColumnAsString(int column) {
        if(column >= 0 && column < board[0].length) {
            String s = "";
            int row = board.length - 1;
            while(row >= 0 && board[row][column] != Location.EMPTY) {
                s += (board[row][column] == Location.RED)? "R" : "B";
                row--;
            }
            return s;
        }
        else {
            return "";
        }
    }
    
    /**
     * This constructor creates a completely empty board and
     * sets the current player to red.
     */
    public ConnectFour() {
        board = new Location[6][7];
        redTurn = true;
        blackTurn = false;
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = Location.EMPTY;
            }
        }
    }
    
    
    /**
     * This method returns the token type on the top of the 
     * specified column. If the column is empty, it should
     * return EMPTY. Otherwise, it should return either
     * RED or BLACK.
     * 
     * @param column the column to check
     * @return the top of the column
     */
    public Location getTopOfColumn(int column) {
        for(int i = 5; i >= 0; i--) {
            if (board[i][column] != Location.EMPTY){
				return board[i][column];
			}
		}
        return Location.EMPTY;
    }
    
    /**
     * This method returns the height of the specified column.
     * If the column is empty, this returns a 0. The maximum
     * value that this function can return is 6.
     * 
     * @param column the column to check
     * @return the height of the column
     */

    public int getHeightOfColumn(int column) {      
        int height = 0;
        for(int i = 5; i >= 0; i--){
            if(board[i][column] != Location.EMPTY){
                height++;
            }
        }
        return height;
    }
    
    
    /**
     * This method drops a token into the specified column and 
     * changes the current player (from RED to BLACK and vice versa).
     * If the column is invalid (i.e., not in the range [0, 6]), then
     * the drop should be ignored (so that it would remain the previous
     * player's turn). If the column is full, it should throw a
     * ColumnFullException (but the player should also not lose their turn).
     * 
     * @param column the column for the token
     */
    public void dropToken(int column) {
        try {
            if (redTurn && column >= 0 && column <= 6){
                board[getHeightOfColumn(column)][column] = Location.RED;
                redTurn = false;
                blackTurn = true;
            } 
			else if (blackTurn && column >= 0 && column <= 6) {
                board[getHeightOfColumn(column)][column] = Location.BLACK;
                blackTurn = false;
                redTurn = true;
            }
        } catch(ArrayIndexOutOfBoundsException ex) {
            throw new ColumnFullException("The column selected, [" + column + "], was full.");
        }
    }

    
    /**
     * This method returns the current result of the game. If the
     * game board is entirely full, this result should be TIE. If
     * RED or BLACK have four-in-a-row, the result should be REDWIN
     * or BLACKWIN, respectively. Otherwise, the result should be
     * NONE (meaning the game is not over).
     * 
     * @return the current result of the game
     */
    public Result getResult() {
		for (int i = 0; i < 6; i++){
			if(getColumnAsString(i).equals("RRRR")){
				return Result.REDWIN;
			}
			else if (getColumnAsString(i).equals("BBBB")){
				return Result.BLACKWIN;
			}
			else{
				return Result.NONE;
			}
		}
    return Result.NONE;
    }
    
    /**
     * This method returns the representation of the board as a string.
     * The following is an example:
     * 
     *  | | | | | | | |
     *  | | | | | | | |
     *  | | | | | | | |
     *  | | | |B| | | |
     *  |B| |B|R| | | |
     *  |R|B|B|R| |R|R|
     *  ---------------
     * 
     * Here, "B" and "R" mark the black and red tokens in each column.
     * 
     * @return a string representing the board
     */
	@Override
    public String toString() {
		String board = "";
		board.equals(getColumnAsString(1));
        return board;
    }

}

