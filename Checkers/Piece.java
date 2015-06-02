
/**
 * Defines a Piece object, (only stores colour and King status)
 * @author Hyun Choi, Ted Pyne, Patrick Forelli
 */

public class Piece
{
    private boolean isWhite, isKing;
    public Piece(boolean isWhite, boolean isKing){
        this.isWhite = isWhite;
        this.isKing = isKing;
    }
    
    public Piece(boolean isWhite){
        this(isWhite, false);
    }
    
    public boolean getIsKing(){
        return isKing;
    }
    
    public boolean getIsWhite(){
        return isWhite;
    }
}
