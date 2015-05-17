
/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board
{
    private Piece[][] board;
    private boolean whiteTurn;                                                   //White has even numbered turns, black odd
    private boolean captureMade;
    private boolean lastMoveDouble;
    private int lastX, lastY;

    public Board(){
        board = new Piece[8][8];
        whiteTurn = true;
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

    public boolean isValidSelection(int xpos, int ypos){                 //If the selected piece is owned by the current player's turn
        if(board[xpos][ypos]==null) return false;
        return board[xpos][ypos].getIsWhite()==whiteTurn;                  //Return if the board piece is a white one == the current player turn;
    }

    public boolean isEmpty(int xpos, int ypos){
        System.out.println("IN ISEMPTY");
        return board[xpos][ypos]==null;
    }

    public boolean isWhiteTurn(){ return whiteTurn;}

    public Piece getPiece (int xpos, int ypos)
    {
        return board[xpos][ypos];   
    }

    /**
     * private methods to check for move validity for different colors
     */
    public boolean makeMove(int xpos, int ypos, int newXPos, int newYPos){
        TurnProcessor turnProc = new TurnProcessor(xpos, ypos, newXPos, newYPos, this);
        if(lastMoveDouble){
            if(xpos!=lastX && ypos !=lastY) return false;
            turnProc.isValidTurn();
            if(!turnProc.wasMoveCapture()) return false;
        }
        else if(!isValidSelection(xpos, ypos)) return false;
        if(board[newXPos][newYPos]!=null) return false;
        if(turnProc.isValidTurn()){
            lastMoveDouble = false;
            doMove(xpos, ypos, newXPos, newYPos);
            kingPromoter(newXPos, newYPos);
            if(turnProc.wasMoveCapture()) makeCapture(turnProc);
            
            if(turnProc.wasMoveCapture() && doubleMove(newXPos, newYPos)){
                lastMoveDouble = true;
                lastX = newXPos;
                lastY = newYPos;
            }
            else nextPlayer();
            return true;
        }
        return false;
    }
    
    private void doMove(int xpos, int ypos, int newXPos, int newYPos){
        board[newXPos][newYPos] = board[xpos][ypos];
        board[xpos][ypos] = null;
    }

    private boolean doubleMove(int xpos, int ypos){                             //if another capture is possible
        int[] newXP = {xpos + 2, xpos - 2};
        int[] newYP = {ypos + 2, ypos - 2};
        for(int x: newXP)
            for(int y: newYP)
                if(x > -1 && y > -1 && x < board.length && y < board.length && isEmpty(x,y)){
                    TurnProcessor turnProc = new TurnProcessor(xpos, ypos, x, y, this);
                    if(turnProc.isValidTurn()) return true;
                }
        return false;
    }

    private void kingPromoter(int xpos, int ypos){
        if(board[xpos][ypos].getIsWhite() && xpos == board.length -1) board[xpos][ypos].makeKing();
        else if(!board[xpos][ypos].getIsWhite() && xpos == 0) board[xpos][ypos].makeKing();
    }

    private void nextPlayer(){
        whiteTurn = !whiteTurn;
    }

    private void makeCapture(TurnProcessor turnProc){
        int[] middle = turnProc.getCaptureTarget();
        board[middle[0]][middle[1]]=null;
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
