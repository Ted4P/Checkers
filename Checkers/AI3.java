import java.util.ArrayList;


public class AI3 extends MoveAI{

	private static final int BASE_RECUR = 3;
	private static double aggression;       //Higher values result in a more defensive AI

	private ArrayList<Move> moves = new ArrayList<Move>();

	public AI3(Board board, boolean isWhite) {
		super(board, isWhite);
		aggression = 1.5;
	}

	public AI3(Board board){
		this(board,false);
	}

	public boolean makeMove() {
		Node<Board> moveTree = new Node<Board>(board);
		makeTree(BASE_RECUR, moveTree);
		return chooseMove(moveTree);
	}

	private boolean chooseMove(Node<Board> moveTree) {
		int size = moveTree.getChildNum();
		double highest = Integer.MIN_VALUE;
		Node<Board> best = null;
		
		for(int i = 0; i < size; i++){
			Node<Board> child = moveTree.getChild(i);
			double score = findChildScore(child);
			if(score >highest){
				highest = score;
				best = child;
			}
		}
		if(best==null) return false;
		return board.makeMove(best.getBoard().getLastMove());
	}
	
	private double findChildScore(Node<Board> node){
		if(node.getChildNum()==0)
			return node.getBoard().getBoardScore(isWhite, aggression);
		double score = 0;
		int size = node.getChildNum();
		for(int i = 0; i < size; i++){
			Node<Board> child = node.getChild(i);
			score+= findChildScore(child) + child.getBoard().getBoardScore(isWhite,aggression);
		}
		return score;
	}

	public void makeTree(int recurLeft, Node<Board> node){
		if(recurLeft==0){ 
		return;
		}
		
		addChildren(node);
		int size = node.getChildNum();
		for(int i = 0; i < size; i++)
			makeTree(recurLeft-1, node.getChild(i));
		return;
	}

	public boolean addChildren(Node<Board> node) {
		moves = findPosMoves(node.getBoard());
		if(moves.size()==0) return false;
		for(Move move: moves){
			Board testBoard = new Board(board);
			testBoard.makeMove(move);
			node.addChild(testBoard);
		}
		return true;
	}



	private ArrayList<Move> findPosMoves(Board board){
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
