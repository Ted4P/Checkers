
/**
 * Processes turns
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TurnProcessor
{
    private int x, y, newX, newY;
    private Board board;
    private boolean moveIsCapture;

    public TurnProcessor(int x, int y, int newX, int newY, Board board){
        this.x = x; this.y = y;
        this.newX = newX; this.newY = newY;
        this.board = board;
    }

    public boolean isValidTurn(){
        moveIsCapture = false;
        boolean valid = false;
        if(board.getPiece(x,y).getIsKing() && kingValidMove()){
            return true;
        }
        else if(board.getPiece(x,y).getIsWhite() && whiteValidMove()){
            return true;
        }
        else if (blackValidMove()){
            return true;
        }
        return false;
    }

    public boolean wasMoveCapture(){
        return moveIsCapture;
    }

    private boolean whiteValidMove(){
        System.out.println("IN WHITEVALIDMOVE");
        if(x - newX == -1 && Math.abs(y - newY)==1)  return true;                    //If it is "down" the board one in either direction
        if(x - newX == -2 && Math.abs(y - newY)==2 && isValidCapture()){
            moveIsCapture = true;
            return true;
        }
        return false;
    }

    private boolean blackValidMove(){
        if(x - newX == 1 && Math.abs(y - newY)==1) return true;
        if(x - newX == 2 && Math.abs(y - newY)==2 && isValidCapture()){
            moveIsCapture = true;
            return true;
        }
        return false;
    }

    private boolean kingValidMove(){
        System.out.println("IN KINGVALIDMOVE");
        if(Math.abs(x - newX)==1&& Math.abs(y - newY)==1) return true;              //No direction checking is required
        if(Math.abs(x - newX)==2&& Math.abs(y - newY)==2 && isValidCapture()) {
            moveIsCapture = true; 
            return true;
        }
        return false;
    }

    private boolean isValidCapture(){
        int[] middle = getCaptureTarget();
        if(board.isEmpty(middle[0],middle[1])) return false;
        return board.getPiece(middle[0],middle[1]).getIsWhite()!=board.isWhiteTurn();
    }

    public int[] getCaptureTarget(){
        int[] middle = new int[2];
        if(x > newX)
            middle[0] = x - 1;
        else middle[0] = x + 1;
        if(y > newY)
            middle[1] = y - 1;
        else middle[1] = y + 1;
        return middle;
    }
}
