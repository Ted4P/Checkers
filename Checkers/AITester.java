import java.util.Scanner;


public class AITester {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		Board board = new Board();
		
		System.out.println("Agression of first AI:");
		AI3 ai1 = new AI3(board, true);
		ai1.setAggression(scan.nextDouble());
		
		System.out.println("Agression of second AI:");
		AI3 ai2 = new AI3(board, false);
		ai2.setAggression(scan.nextDouble());
		
		System.out.println("Number of turns:");
		int i = scan.nextInt();
		
		AI3.setRecur(5);
		while(i>0){
			while(ai1.makeMove());
			while(ai2.makeMove());
			i--;
		}
		System.out.println("The score for ai1 is: " + board.getBoardScore(true, 1));
		System.out.println("The score for ai2 is: "+ board.getBoardScore(false, 1));
		System.out.println(board.toString());
	}
}
