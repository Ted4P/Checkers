public class AI{
    private Board board;
    public AI(Board board){
        this.board = board;
    }

    public boolean makeMove(){
        if(board.isWhiteTurn()) return false; 
        //First attempt a capture move
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                if(board.isValidSelection(x,y)){

                    int[] newX = {x+2, x-2};
                    int[] newY = {y+2, y-2};
                    for(int tryX: newX)
                        for(int tryY: newY)
                            if(validTarget(tryX, tryY) && calcCapture(x,y,tryX,tryY)) return true;

                    newX[0] = x + 1; newX[1] = x - 1;
                    newY[0] = y + 1; newY[1] = y - 1;
                    for(int tryX: newX)
                        for(int tryY: newY)
                            if(validTarget(tryX, tryY) && board.makeMove(x,y,tryX,tryY)) return true;
                }
            }
        }
        return false;
    }

    private boolean calcCapture(int x, int y, int capX, int capY){
        if(board.makeMove(x,y,capX,capY))   //If a possible capture (with double moves) is possible, make it
            if(board.isWhiteTurn()) return true;
            else {
                int[] newX = {x+2, x-2};
                int[] newY = {y+2, y-2};
                for(int tryX: newX)
                    for(int tryY: newY)
                        if(validTarget(tryX, tryY) && calcCapture(x,y,tryX,tryY)) return true;
            }
        return false;
    }
    
    private boolean validTarget(int x, int y){
    return x > -1 && x < 8 && y > -1 && y < 8;
    }
}