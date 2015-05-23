
/**
 * Defines a Piece object, (only stores colour and King status)
 * @author Hyun Choi, Ted Pyne, Patrick Forelli
 */

public class Piece
{
    private boolean isWhite, isKing;
    public Piece(boolean isWhite){
        this.isWhite = isWhite;
        isKing = false;
    }
    
    public boolean getIsKing(){
        return isKing;
    }
	
	public void makeKing(){
		isKing = true;
	}
    
    public boolean getIsWhite(){
        return isWhite;
    }
}
