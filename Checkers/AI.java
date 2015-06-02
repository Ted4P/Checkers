/**
 *
 * @author Hyun Choi, Ted Pyne, Patrick Forelli
 */
import java.util.ArrayList;
public class AI{

    private Board board;
    private boolean isWhite, madeCapture;
    private ArrayList<Move> posMoves;
    private final int BASE_RECUR = 4;
    public AI(Board board, boolean isWhite){     //Store a board to make moves on
        this.board = board;
        this.isWhite = isWhite;
    }

    public AI(Board board){
        this(board, false);
    }

    int makeMove(int recurLeft){
        System.out.println("How many recusions " + recurLeft);
        if(board.isWhiteTurn()!=isWhite) return -1;        
        //First attempt a capture move
        posMoves = new ArrayList<Move>();

        for(int x = 0; x < 8; x++){                   //Find possible captures
            for(int y = 0; y < 8; y++){
                if(board.isValidSelection(x,y)){
                    int[] newX = {x+2, x-2};        //Generate possible moves
                    int[] newY = {y+2, y-2};
                    for(int tryX: newX)
                        for(int tryY: newY)
                            if(validTarget(tryX, tryY) && calcCapture(x,y,tryX,tryY)){
                                if(recurLeft==0) {
                                        board.makeMove(x,y,tryX,tryY); 
                                        return 10;
                                    }
                                    else posMoves.add(new Move(x,y,tryX,tryY));
                            }     //Use submethod to check for valid capture
                }
            }
        }   
        for(int x = 0; x < 8; x++){                   //If no capture is found, look for normal moves
            for(int y = 0; y < 8; y++){
                if(board.isValidSelection(x,y)){
                    int[] newX = {x+1, x-1};        //Generate possible moves
                    int[] newY = {y+1, y-1};
                    for(int tryX: newX)
                        for(int tryY: newY)
                            if(validTarget(tryX, tryY)){
                                Board newMove = new Board(board);
                                if(newMove.makeMove(x,y,tryX,tryY)){ 
                                    if(recurLeft==0) {
                                        board.makeMove(x,y,tryX,tryY); 
                                        return 0;
                                    }
                                    else posMoves.add(new Move(x,y,tryX,tryY));
                                }
                            }
                }
            }
        }
        if(posMoves.size()==0) return -1;   //If no move can be found, return false

        Move bestMove = posMoves.get(0);
        int bestVal = 0;
        int thisVal = 0;
        for(int i = 0; i < posMoves.size(); i++){
            thisVal = 0;
            
            Board test = new Board(board);
            test.makeMove(posMoves.get(i).getX(), posMoves.get(i).getY(), posMoves.get(i).getNewX(), posMoves.get(i).getNewY());
            AI tester = new AI(test, !isWhite);
            TurnProcessor turnProc = new TurnProcessor(posMoves.get(i).getX(), posMoves.get(i).getY(), posMoves.get(i).getNewX(), posMoves.get(i).getNewY(), board);
            
            if(turnProc.wasMoveCapture()) 
                thisVal =20;
            
            int tryVal = tester.makeMove(recurLeft-1);
            System.out.println("TRYVAL: " + tryVal);
            if(i==0 || tryVal < bestVal){
               bestVal = tryVal;
               bestMove = posMoves.get(i);
            }
        }
        System.out.println("BEST: " + bestVal);
        board.makeMove(bestMove.getX(), bestMove.getY(), bestMove.getNewX(), bestMove.getNewY());
        return thisVal;
    }

    public boolean makeMove(){
        return makeMove(BASE_RECUR) != -1;
    }

    private boolean calcCapture(int x, int y, int capX, int capY){
        Board newMove = new Board(board);
        if(newMove.makeMove(x,y,capX,capY)){   //If a possible capture (with double moves) is possible, perform that capture
            return true;
        }
        return false;
    }

    private boolean validTarget(int x, int y){      //Bounds checking for move and capture targets
        return x > -1 && x < 8 && y > -1 && y < 8;
    }
}