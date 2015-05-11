
/**
 * Write a description of class Piece here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Piece
{
    private boolean isWhite, isKing;
    public Piece(boolean isWhite){
        this.isWhite = isWhite;
        isKing = false;
    }
    
    public boolean getIsKing(){
        return isKing();
    }
    
    public boolean getIsWhite(){
        return isWhite();
    }

    public String toString(){
        if(isKing){
            if(isWhite) 
                return "WW";
            return "BB";
        }
        else{
            if(isWhite)
                return "W";
            return "B";
        }
    }
}
