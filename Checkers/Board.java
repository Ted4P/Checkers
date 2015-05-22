
/**
 * 
 * @author Ted Pyne, Hyun Choi, Patrick Forelli 
 * @version (a version number or a date)
 */
public class Board
{
    private Piece[][] board;                                            //Board state, represented by a 2D array of Piece objects
    private boolean whiteTurn;                                          //Current player turn is tracked in board to enable double-move logic
    private boolean lastMoveDouble;                                     //If the last move allowed a double move, store persistently across turn
    private int lastX, lastY;                                           //During the second phase of a double move, the player must select the piece they moved in the first phase
    private int blackLeft, whiteLeft;                                   //Number of pieces remaining

    public Board(){
        board = new Piece[8][8];
        whiteTurn = true;   //Start with white
        addPieces(); 
    }

    private void addPieces(){
        for(int i = 0; i < 3; i++)  //Correct white rows
            for(int j = i%2; j < board[0].length; j += 2){
                board[i][j] = new Piece(true);                          //Initialise White pieces
                whiteLeft++;
        }

        for(int i = 7; i > 4; i--)  //Correct black rows
            for(int j = i%2; j < board[0].length; j += 2){
                board[i][j] = new Piece(false);                          //Initialise black pieces
                blackLeft++;
        }
    }

    public boolean isValidSelection(int xpos, int ypos){                 //If the selected piece is owned by the current player's turn
        if(board[xpos][ypos]==null) return false;
        return board[xpos][ypos].getIsWhite()==whiteTurn;                  //Compare piece color to current turn
    }

    public boolean isEmpty(int xpos, int ypos){ //return if a selection is empty
        return board[xpos][ypos]==null;
    }

    public boolean isWhiteTurn(){ return whiteTurn;}

    public Piece getPiece (int xpos, int ypos)
    {
        return board[xpos][ypos];   
    }

    /**
     * makeMove does NOT perform array bounds checking; all input params are assumed to be 0<=i<=7
     */
    public boolean makeMove(int xpos, int ypos, int newXPos, int newYPos){
        TurnProcessor turnProc = new TurnProcessor(xpos, ypos, newXPos, newYPos, this);             //Create new turnProcessor
        if(lastMoveDouble){                                     //If this move is the second phase of a double move
            if(xpos!=lastX && ypos !=lastY) return false;       //If the player selects a different piece, return false
            turnProc.isValidTurn();                             //Process coordinates
            if(!turnProc.wasMoveCapture()) return false;        //If the move was not a capture move, return false
        }
        else if(!isValidSelection(xpos, ypos)) return false;    //If the move selection is invalid, return false
        if(!isEmpty(newXPos,newYPos)) return false;             //If the move target is not empty, return false
        if(turnProc.isValidTurn()){ //If a valid move
            lastMoveDouble = false;
            doMove(xpos, ypos, newXPos, newYPos);   //Actually perform the moving of the piece
            kingPromoter(newXPos, newYPos);         //If the piece has reached the end, promote to king
            if(turnProc.wasMoveCapture()) makeCapture(turnProc);    //If the move involved a capture, make that capture

            if(turnProc.wasMoveCapture() && doubleMove(newXPos, newYPos)){  //If a double move is possible
                lastMoveDouble = true;
                lastX = newXPos;    //Store the Piece's coordinates
                lastY = newYPos;
            }
            else nextPlayer();      //Else change player turn
            return true;
        }
        return false;
    }

    private void doMove(int xpos, int ypos, int newXPos, int newYPos){          //No checks whatsoever, just move the piece
        board[newXPos][newYPos] = board[xpos][ypos];
        board[xpos][ypos] = null;
    }

    private boolean doubleMove(int xpos, int ypos){                             //Return true if another capture is possible
        int[] newXP = {xpos + 2, xpos - 2};
        int[] newYP = {ypos + 2, ypos - 2};
        for(int x: newXP)
            for(int y: newYP)
                if(x > -1 && y > -1 && x < board.length && y < board.length && isEmpty(x,y)){   //Make sure the x and y are valid indices
                    TurnProcessor turnProc = new TurnProcessor(xpos, ypos, x, y, this);         //Check if the move is valid
                    if(turnProc.isValidTurn() && turnProc.wasMoveCapture()) return true;        //If a move is a valid capture move, return true
        }
        return false;
    }

    private void kingPromoter(int xpos, int ypos){  //If a piece has reached the opposite side, promote it to a king
        if(board[xpos][ypos].getIsWhite() && xpos == board.length -1) board[xpos][ypos].makeKing();
        else if(!board[xpos][ypos].getIsWhite() && xpos == 0) board[xpos][ypos].makeKing();
    }

    private void nextPlayer(){
        whiteTurn = !whiteTurn;
    }

    private void makeCapture(TurnProcessor turnProc){ //Delete the target of a capture
        int[] middle = turnProc.getCaptureTarget();
        if(board[middle[0]][middle[1]].getIsWhite()) whiteLeft--;
        else blackLeft--;
        board[middle[0]][middle[1]]=null;
    }

    public Piece gameIsWon(){                                            //If white has won, return a white piece, if black has won, return black, else return null
        boolean validMove = anyValidMove();
        if((!validMove && !whiteTurn) || blackLeft==0) return new Piece(true);
        if((!validMove && whiteTurn) || whiteLeft==0) return new Piece(false);
        return null;
    }

    private boolean anyValidMove(){
        for(int xpos = 0; xpos < 8; xpos++){
            for(int ypos = 0; ypos < 8; ypos++){
                int[] newXP = {xpos + 2, xpos - 2, xpos + 1, xpos - 1};
                int[] newYP = {ypos + 2, ypos - 2, ypos + 1, ypos - 1};
                for(int x: newXP)
                    for(int y: newYP)
                        if(x > -1 && y > -1 && x < 8 && y < 8 && isEmpty(x,y) && !isEmpty(xpos,ypos)){   //Make sure the x and y are valid indices
                            TurnProcessor turnProc = new TurnProcessor(xpos, ypos, x, y, this);         //Check if the move is valid
                            if(getPiece(xpos,ypos).getIsWhite() == whiteTurn && turnProc.isValidTurn()) return true;
                        }
            }
        }
        return false;
    }
}