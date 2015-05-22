public class AI{
    private Board board;
    public AI(Board board){     //Store a board to make moves on
        this.board = board;
    }

    public boolean makeMove(){
        if(board.isWhiteTurn()) return false;           //The AI is always black
        //First attempt a capture move

        //Create a random list of rows and cols to try
        int[] rows = {0,1,2,3,4,5,6,7};
        int[] cols = {0,1,2,3,4,5,6,7};
        scrambleArr(rows);
        scrambleArr(cols);                  //Scramble the rows and columns to  create random AI behaviour

        
        for(int x: rows){                   //Find possible captures
            for(int y: cols){
                if(board.isValidSelection(x,y)){
                    int[] newX = {x+2, x-2};
                    int[] newY = {y+2, y-2};
                    for(int tryX: newX)
                        for(int tryY: newY)
                            if(validTarget(tryX, tryY) && calcCapture(x,y,tryX,tryY)) return true;      //Use submethod to check for valid capture
                }
            }
        }   
        for(int x: rows){                   //If no capture is found, look for normal moves
            for(int y: cols){
                if(board.isValidSelection(x,y)){
                    int[] newX = {x+1, x-1};
                    int[] newY = {y+1, y-1};
                    for(int tryX: newX)
                        for(int tryY: newY)
                            if(validTarget(tryX, tryY) && board.makeMove(x,y,tryX,tryY)) return true;
                }
            }
        }
        return false;   //If no move can be found, return false
    }

    private boolean calcCapture(int x, int y, int capX, int capY){
        if(board.makeMove(x,y,capX,capY)){   //If a possible capture (with double moves) is possible, perform that capture
            if(board.isWhiteTurn()) return true;    //If a double move is not possible, exit
            else {
                int[] newX = {x+2, x-2};
                int[] newY = {y+2, y-2};
                for(int tryX: newX)
                    for(int tryY: newY)
                        if(validTarget(tryX, tryY) && calcCapture(x,y,tryX,tryY)) return true;  //If a double move is possible, try all follow up moves recursively
            }
        }
            return false;
    }
    
    private boolean validTarget(int x, int y){      //Bounds checking for move and capture targets
        return x > -1 && x < 8 && y > -1 && y < 8;
    }
    
    private void scrambleArr(int[] arr){        //Scramble an array
        for(int i = 0; i < 8; i++){
            int sPos = (int)(Math.random()*8);
            int ePos = (int)(Math.random()*8);
            int temp = arr[ePos];
            arr[ePos] = arr[sPos];
            arr[sPos] = temp;
        }
    }
    
}