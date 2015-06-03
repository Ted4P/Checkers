
public abstract class MoveAI {
	protected Board board;
	protected boolean isWhite;
	public abstract boolean makeMove();
	public MoveAI(Board board, boolean isWhite) {
		this.board = board;
		this.isWhite = isWhite;
	}
}
