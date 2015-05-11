
/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board
{
    private Piece[][] board;
    private int turn;                                                   //White has even numbered terms, black odd
    
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

    public Piece gameIsWon(){                                            //If white has won, return a white piece, if black has won, return black, else return null
        boolean blackAlive = false, whiteAlive = false;
        for(Piece[] row: board){
            for(Piece space: row){
                if(space!=null){
                    if(space.getIsWhite()) whiteAlive = true;
                    else blackAlive = true;
                }
            }
        }
        
        if(blackAlive && whiteAlive) return  null;
        if(blackAlive) return new Piece(false);
        return new Piece(true);
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
