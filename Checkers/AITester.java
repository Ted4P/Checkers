
public class AITester {
	
	public static void main(String[] args){
		//Linked lists, screw OO
		double[] settings = {.25,.5,.75,1,1.25,1.5,1.75,2};
		int[] wins = new int[settings.length];
		
		int l = settings.length;
		for(int i = 0 ; i < l; i ++){
			for(int j = i + 1; j < l; j++){
				double result[] = testAI(i,j,18);
				if(result[0]> result[1]) wins[i]++;
				else if(result[1]>result[0]) wins[j]++;
			}
		}
		for(int r = 0; r < l; r++){
			System.out.println("AI aggr " + settings[r] + " won " + wins[r] + " games.");
		}
	}
	
	private static double[] testAI(int AI1, int AI2, int moves){
		Board board = new Board();
		AI3 ai1 = new AI3(board, true);
		ai1.setAggression(AI1);
		AI3 ai2 = new AI3(board, false);
		ai2.setAggression(AI2);

		AI3.setRecur(5);
		while(moves>0){
			while(ai1.makeMove());
			while(ai2.makeMove());
			moves--;
		}
		double[] score = new double[2];
		score[0] = -board.getBoardScore(false, 0);
		score[1] = -board.getBoardScore(true, 0);
		return score;
	}
}
