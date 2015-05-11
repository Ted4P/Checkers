
/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board
{
    private Piece[][] board;
    public Board(){
        board = new Piece[8][8];
        addPieces();
    }

    private void addPieces(){
        for(int i = 0; i < 3; i++)
            for(int j = i%2; j < board[0].length; j += 2)
                board[i][j] = new Piece(true);                          //Initialise White pieces
                
        for(int i = 7; i > 4; i--)
            for(int j = i%2; j < board[0].length; j += 2)
                board[i][j] = new Piece(false);                          //Initialise black pieces
    }

    /**
     * Degugging method: Print board to console
     */
    public void printArr(){
        for(int i = 0; i < board.length; i++){
            
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j]==null) 
                System.out.print("| ");
                else
                System.out.print("|" + board[i][j]);

            }
            System.out.println("|");
        }
    }
}
