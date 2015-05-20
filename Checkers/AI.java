public class AI{
    private Board board;
    public AI(Board board){
        this.board = board;
    }

    public boolean makeMove(){
        if(board.isWhiteTurn()) return false; 
        //First attempt a capture move
        //Create a random list of rows and cols to try
        int[] rows = {0,1,2,3,4,5,6,7};
        int[] cols = {0,1,2,3,4,5,6,7};
        scrambleArr(rows);
        scrambleArr(cols);
        
        for(int x: rows){
            for(int y: cols){
                if(board.isValidSelection(x,y)){
                    int[] newX = {x+2, x-2};
                    int[] newY = {y+2, y-2};
                    for(int tryX: newX)
                        for(int tryY: newY)
                            if(validTarget(tryX, tryY) && calcCapture(x,y,tryX,tryY)) return true;
                }
            }
        }
        for(int x: rows){
            for(int y: cols){
                if(board.isValidSelection(x,y)){
                    System.out.println("VALID SELECTION");
                    int[] newY = {y+2, y-2};
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