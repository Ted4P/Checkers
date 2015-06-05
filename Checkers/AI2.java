import java.util.ArrayList;


public class AI2 extends MoveAI{

    private static int baseRecur;
    private static double aggression;       //Higher values result in a more defensive AI

    public AI2(Board board, boolean isWhite) {
        super(board, isWhite);
        aggression = 1.5;
        baseRecur = 4;
    }

    public AI2(Board board){
        this(board,false);
    }

    public static void setAggression (double newVal){
        aggression = newVal;
    }
    
    public static void setRecur (int newVal){
        baseRecur = newVal;
    }
    
    public boolean makeMove() {
        return makeMove(baseRecur);
    }

    public boolean makeMove(int recurLeft) {
        if(board.isWhiteTurn()!=isWhite) return false; //If it's not the AI's turn, return false
        ArrayList<Move> moves = findPosMoves();
        if(moves.size()==0) return false;
        if(recurLeft==0){            //If the base level of recursion has been reached
            for(Move move:moves){
                if(Math.abs(move.getX()-move.getNewX())==2) return board.makeMove(move);
            }
            return board.makeMove(moves.get((int)Math.random()*moves.size()));
        }
        double bestMoveScore =100;
        Move bestMove = moves.get(0);
        for(Move move: moves){
            Board testBoard = new Board(board);
            testBoard.makeMove(move);

            AI2 ai = new AI2(testBoard, !isWhite);
            double moveScore = -1 * this.getGameScore();
            while(ai.makeMove(recurLeft-1));        //Go though all AI moves (work with double moves)
                moveScore += ai.getGameScore();
            if(moveScore<=bestMoveScore){ 
                bestMoveScore=moveScore;
                bestMove = move;
            }
        }
        return board.makeMove(bestMove);
    }

    public double getGameScore(){
        int blackScore=0, whiteScore=0;
        for(int x = 0; x < 8; x++)
            for(int y = 0; y < 8; y++){
                Piece piece = board.getPiece(x,y);
                if(piece!=null && piece.getIsWhite()){
                    if(piece.getIsKing()) whiteScore++;
                        whiteScore+=2;
                }
                else if(piece!=null){
                    if(piece.getIsKing()) blackScore++;
                        blackScore+=2;
                }

            }
        if(isWhite) return (whiteScore * aggression) - blackScore;
        else return (blackScore * aggression) - whiteScore;
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
