import java.util.ArrayList;


public class AI2 extends MoveAI{

	private static final int BASE_RECUR = 4;

	public AI2(Board board, boolean isWhite) {
		super(board, isWhite);
	}

	public AI2(Board board){
		this(board,false);
	}
	
	public boolean makeMove() {
		return makeMove(BASE_RECUR);
	}

	public boolean makeMove(int recurLeft) {
		if(board.isWhiteTurn()!=isWhite) return false; //If it's not the AI's turn, return false
		ArrayList<Move> moves = findPosMoves();
		if(moves.size()==0) return false;
		if(recurLeft==0)			//If the base level of recursion has been reached
			return board.makeMove(findPosMoves().get(0));

		
		int bestMoveScore =100;
		Move bestMove = moves.get(0);
		for(Move move: moves){
			Board testBoard = new Board(board);
			testBoard.makeMove(move);

			AI2 ai = new AI2(testBoard, !isWhite);
			int moveScore = 0;
			while(ai.makeMove(recurLeft-1));		//Go though all AI moves (work with double moves)
			moveScore += ai.getGameScore();
			if(moveScore<bestMoveScore){ 
				bestMoveScore=moveScore;
				bestMove = move;
			}
		}
		return board.makeMove(bestMove);
	}

	public int getGameScore(){
		int blackScore=0, whiteScore=0;
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++){
				Piece piece = board.getPiece(x,y);
				if(piece!=null && piece.getIsWhite()) whiteScore++;
				else if(piece!=null) blackScore++;

			}
		if(isWhite) return whiteScore*2  - blackScore;
		else return blackScore*2 - whiteScore;
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
