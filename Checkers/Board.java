
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
    
    private boolean isValidSelection(int xpos, int ypos){                 //If the selected piece is owned by the current player's turn
        System.out.println("DEBUG: IN ISVALIDSELECTION");
        if(board[xpos][ypos]==null) return false;
        System.out.println("BOARD SELECTION IS NOT NULL");
        return board[xpos][ypos].getIsWhite()==whiteTurn;                  //Return if the board piece is a white one == the current player turn;
    }
    
    private boolean isEmpty(int xpos, int ypos){
        System.out.println("IN ISEMPTY");
        return board[xpos][ypos]==null;
    }
    
    public boolean isWhiteTurn(){ return whiteTurn;}
    
    
    /**
     * private methods to check for move validity for different colors
     */
    
    private boolean whiteValidMove(int xpos, int ypos, int newXPos, int newYPos){
        System.out.println("IN WHITEVALIDMOVE");
        if(xpos - newXPos == -1 && Math.abs(ypos - newYPos)==1)  return true;                    //If it is "down" the board one in either direction
        if(xpos - newXPos == -2 && Math.abs(ypos - newYPos)==2 && isValidCapture(xpos++, ypos - (ypos - newYPos))){makeCapture(xpos++, ypos - (ypos - newYPos)); return true;}
        return false;
    }
    
    private boolean blackValidMove(int xpos, int ypos, int newXPos, int newYPos){
        if(xpos - newXPos == 1 && Math.abs(ypos - newYPos)==1) return true;
		if(xpos - newXPos == 2 && Math.abs(ypos - newYPos)==2 && isValidCapture(xpos--, ypos - (ypos - newYPos))){ makeCapture(xpos--, ypos - (ypos - newYPos)); return true;}
		return false;
    }
    
    private boolean kingValidMove(int xpos, int ypos, int newXPos, int newYPos){
        System.out.println("IN KINGVALIDMOVE");
        if(Math.abs(xpos - newXPos)==1&& Math.abs(ypos - newYPos)==1) return true;              //No direction checking is required
        if(Math.abs(xpos - newXPos)==2&& Math.abs(ypos - newYPos)==2 && isValidCapture(xpos - (xpos - newXPos), ypos - (ypos - newYPos))) 
			{ makeCapture(xpos - (xpos - newXPos), ypos - (ypos - newYPos)); return true;}
        return false;
    }
    
    private boolean isValidCapture(int xpos, int ypos){
        if(isEmpty(xpos,ypos)) return false;        
		return board[xpos][ypos].getIsWhite()!=whiteTurn;
    }
	
	private void makeCapture(int xpos, int ypos){
		board[xpos][ypos]=null;
	}
	
	private void doMove ( int xpos, int ypos, int newXPos, int newYPos){
		board[newXPos][newYPos] = board[xpos][ypos];
		board[xpos][ypos]=null;
	}
    
    /**
     * BCEAUAE OF THE GUI DESIGN, ALL INPUT IS ASSUMED TO BE SANITIZED!
     */
    public boolean makeMove(int xpos, int ypos, int newXPos, int newYPos){
        if(!isValidSelection(xpos, ypos)) return false;
        if(isEmpty(xpos, ypos)) return false;
		boolean valid = false;
        if(board[xpos][ypos].getIsKing() && kingValidMove(xpos, ypos, newXPos, newYPos)){
				valid = true;
		}
        else if(board[xpos][ypos].getIsWhite() && whiteValidMove(xpos, ypos, newXPos, newYPos)){
			valid = true;
		}
        else if (blackValidMove(xpos, ypos, newXPos, newYPos)){
			valid = true;
		}
		else return false;
		if(valid){ doMove(xpos, ypos, newXPos, newYPos);
			kingPromoter(newXPos, newYPos);
			if(!doubleMove(newXPos, newYPos)) nextPlayer(); 
			
		}
		return true;
    }
	
	private boolean doubleMove(int xpos, int ypos){ 							//if another capture is possible
		int[] newXP = {xpos + 2, xpos - 2};
		int[] newYP = {ypos + 2, ypos - 2};
		for(int x: newXP)
			for(int y: newYP)
				if(x > -1 && y > -1 && x < board.length && y < board.length && isEmpty(x,y) && isValidCapture(xpos - (xpos - newXPos), ypos - (ypos - newYPos))) return true;
		return false;
	}
	
	private void kingPromoter(int xpos, int ypos){
		if(board[xpos][ypos].getIsWhite() && xpos == board.length -1) board[xpos][ypos].makeKing();
		else if(!board[xpos][ypos].getIsWhite() && xpos == 0) board[xpos][ypos].makeKing();
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
