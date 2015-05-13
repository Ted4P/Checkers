
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class Game
{
    public static void main(String[] args){
        Board board = new Board();
        board.printArr();
        System.out.println("\n"+ board.gameIsWon());

    }

    public static void test(){
        Scanner scan = new Scanner(System.in);
        Board board = new Board();
        while(true){
            board.printArr();
            int x = scan.nextInt();
            int y = scan.nextInt();
            int newX = scan.nextInt();
            int newY = scan.nextInt();
            board.makeMove(x,y,newX,newY);
        }
    }
}
