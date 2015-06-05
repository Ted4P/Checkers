import java.util.ArrayList;


public class AI3 extends MoveAI{

    private static final int BASE_RECUR = 4;
    private static double aggression;       //Higher values result in a more defensive AI
    
    private double moveVal;
    private ArrayList<Move> moves = new ArrayList<Move>();

    public AI3(Board board, boolean isWhite) {
        super(board, isWhite);
        aggression = 1.5;
    }

    public AI3(Board board){
        this(board,false);
    }

    public boolean makeMove() {
        return makeMove(BASE_RECUR);
    }

    public double getTotalVal(){ return moveVal;}
    
    public boolean makeMove(int recurLeft) {
        if(board.isWhiteTurn()!=isWhite) return false; //If it's not the AI's turn, return false
        moves = findPosMoves();
        if(moves.size()==0) return false;
        moveVal = 0;
        if(recurLeft==0){            //If the base level of recursion has been reached
            
        	double bestScore = -100;
        	Move bestMove = null;
        	
            for(Move move:moves){
        		Board testBoard = new Board(board);
                testBoard.makeMove(move);

                double score = testBoard.getBoardScore(isWhite, aggression);
                moveVal += score;
                if(score > bestScore){
                	bestMove = move;
                	bestScore = score;
                }
            }
            return board.makeMove(bestMove);
        }
        else{
        	double bestScore = -100;
        	Move bestMove = null;
        	for(Move move: moves){
        		Board testBoard = new Board(board);
        		testBoard.makeMove(move);

        		AI3 ai = new AI3(testBoard, !isWhite);
        		while(ai.makeMove(recurLeft-1));        //Go though all AI moves (work with double moves)
        		double score = ai.getTotalVal();
        		if(recurLeft==BASE_RECUR) System.out.println("Score: " + score);
        		
        		if(score > bestScore){
        			bestScore=score;
        			bestMove = move;
        		}
        		moveVal+=score;
        	}
        	if(recurLeft==BASE_RECUR) System.out.println("Best: " + bestScore);
        	return board.makeMove(bestMove);
        }
    }
    


    private ArrayList<Move> findPosMoves(){
        ArrayList<Move> moves = new ArrayList<Move>();
        for(int x = 0; x < 8; x++){                   //Find possible captures
            for(int y = 0; y < 8; y++){
                if(board.isValidSelection(x,y)){
                    int[] newX = {x+2, x-2};        //Generate possible moves
                    int[] newY = {y+2, y-2};
                    for(int tryX: newX)
                        for(int tryY: newY){
                            if(validTarget(tryX, tryY)){
                                Board newMove = new Board(board);
                                if(newMove.makeMove(x,y,tryX,tryY)){
                                    moves.add(new Move(x,y,tryX,tryY));
                                }
                            }
                        }
                    newX[0] = x + 1; newX[1] = x - 1;
                    newY[0] = y + 1; newY[1] = y - 1;
                    for(int tryX: newX)
                        for(int tryY: newY)
                            if(validTarget(tryX, tryY)){
                                Board newMove = new Board(board);
                                if(newMove.makeMove(x,y,tryX,tryY)){ 
                                    moves.add(new Move(x,y,tryX,tryY));
                                }
                            }
                }
            }
        }
        return moves;
    }


    private boolean validTarget(int x, int y){      //Bounds checking for move and capture targets
        return x > -1 && x < 8 && y > -1 && y < 8;
    }

}
