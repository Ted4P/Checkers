
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Hyun Choi, Ted Pyne, Patrick Forelli
 */
public class CheckersGUI extends javax.swing.JFrame  {
    //keeps track of a Board, a 2d array of JLabels to represent each tile, and JPanel to store the tiles
    public Board board; 
    private JLabel[][] GUIboard;

    //JPanel entireGUI for the enclosure of both the board and the text
    private JPanel entireGUI;

    //outer JPanel panel for the outer board panel, boardGUI for the inner board panel
    private JPanel panel;
    private JPanel boardGUI;

    //JPanel for textual info; JLabels/JButton for information and toggling
    private JPanel text;
    private JLabel victoryStatus;
    private JLabel turnStatus;
    private JButton aiToggle;

    //AI implementation
    private MoveAI ai; 
    private boolean aiActive;

    private boolean selected = false; //if a piece is selected or not
    private int[][] currentSelected; //coordinates of the selected piece and the target area
    private final int MULTIPLIER = 62; //width of one tile

    /**
     * Creates new form CheckersGUI
     */
    public CheckersGUI() {
        board = new Board();
        GUIboard = new JLabel[8][8];
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                //if(board.getPiece(i,j) != null)
                GUIboard[i][j] = new JLabel();

            }
        }

        entireGUI = new JPanel(); //outer JPanel to store the boardGUI and the textual information
        entireGUI.setLayout(new BoxLayout(entireGUI, BoxLayout.X_AXIS));

        aiActive = false; //by default, AI is inactive

        text = new JPanel(); //inner JPanel to hold text
        text.setLayout(new GridLayout (3,2));
        initializeText();
        currentSelected = new int[2][2];
        boardGUI = new JPanel();
        boardGUI.setLayout(new GridLayout(8,8)); //tiles in a GridLayout of 8x8
        boardGUI.addMouseListener(new MouseAdapter() { //essence of the GUI's click detection

                int selected =0;

                public void mouseClicked(MouseEvent e) {
                    if (selected==0) //if nothing is selected
                    {
                        currentSelected[0]=arrayCoord(pressed(e)); //store coordinates of the press in array
                        selected++;
                        //if invalid selection, revert
                        if(!board.isValidSelection(currentSelected[0][1], currentSelected[0][0])){
                            currentSelected = new int[2][2];
                            selected=0;
                        }
                        else {
                            //If a valid selection has been made, highlight the piece to the user
                            int i = currentSelected[0][1]; 
                            int j = currentSelected[0][0];
                            if (board.getPiece(i,j).getIsWhite())//if the piece is white
                            {
                                if (board.getPiece(i,j).getIsKing())
                                    GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithwhitekingselected.png")));
                                else 
                                    GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithwhiteselected.png")));

                            }
                            else //so that means it's a red
                            {
                                if (board.getPiece(i,j).getIsKing())
                                    GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithredkingselected.png")));
                                else 
                                    GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithredselected.png")));
                            }  
                        }
                    }
                    else if (selected ==1) //Target tile
                    {
                        //using the coordinates, make a move and render the board on the GUI
                        currentSelected[1]=arrayCoord(pressed(e));
                        TurnProcessor turnProc = new TurnProcessor(currentSelected[0][1], currentSelected[0][0], currentSelected[1][1], currentSelected[1][0], board);
                        if(currentSelected[1][1]==currentSelected[0][1] && currentSelected[0][0] == currentSelected[1][0]){ //If the player clicked on their first selection, deselect it
                            currentSelected = new int[2][2];
                            selected=0;
                            renderBoard();
                        }
                        else if(!turnProc.isValidTurn()){   //If the selection is invalid, wait for a valid one
                            selected = 1;
                        } else{         //If a valid selection, do the move
                            move(currentSelected);
                            renderBoard();
                            //revert to original state
                            currentSelected = new int[2][2];
                            selected=0;
                        }
                        makeAllAIMoves();
                    }

                }
            });
        panel = new JPanel(); //enclose GridLayout within JPanel on the JFrame
        panel.add(boardGUI);
        renderBoard(); //render board on the GUI

    }

    public void renderBoard() //method to arrange images to form the board
    {

        boolean previousColorIsWhite = false; //for arrangement

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (board.getPiece(i,j) != null)    //Get the piece at that space in the board
                {
                    if (board.getPiece(i,j).getIsWhite())//if the piece is white
                    {
                        if (board.getPiece(i,j).getIsKing())
                            GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithwhiteking.png")));
                        else 
                            GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithwhite.png")));

                    }
                    else //so that means it's a red
                    {
                        if (board.getPiece(i,j).getIsKing())
                            GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithredking.png")));
                        else 
                            GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithred.png")));
                    }

                    previousColorIsWhite=true;
                }
                else //if no piece, then blank tile (white or green)
                {
                    if (previousColorIsWhite)
                        GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/greentile.png")));
                    else
                        GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitetile.png")));

                    previousColorIsWhite = !previousColorIsWhite;
                }
                boardGUI.add(GUIboard[i][j]);
            }
            previousColorIsWhite=!previousColorIsWhite;
        }

        refreshText(); //update the text fields
        //combine the two components of the GUI
        entireGUI.add(panel);
        entireGUI.add(text);

        setResizable(false); //window cannot be resized

        //make it visible
        pack();
        this.setContentPane(entireGUI);
        setVisible(true);
    }
    
    private void makeAllAIMoves(){
    	if(ai!=null)
    	while(!board.isWhiteTurn() && board.gameIsWon()==null){
            ai.makeMove();
            renderBoard();
            try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }

    private void initializeText()
    {
    	
        final JLabel VICTORY = new JLabel ("VICTORY");  //Indicators to show vistory, Turn status and AI
        victoryStatus = new JLabel();

        final JLabel TURN = new JLabel ("TURN");
        turnStatus = new JLabel();

        final JLabel AI = new JLabel ("AI STATUS");
        aiToggle = new JButton("AI INACTIVE");
        
        aiToggle.addActionListener(new ActionListener() { //button for toggling AI activation status

                public void actionPerformed(ActionEvent e)
                {
                    aiActive = !aiActive;
                    if (aiActive)
                    {
                        ai = new AI2(board);
                        aiToggle.setText("AI ACTIVE  ");
                        makeAllAIMoves();
                    }
                    else
                    {
                        aiToggle.setText("AI INACTIVE");
                        ai = null;
                    }
                }

            });

        text.add(VICTORY);
        text.add(victoryStatus);
        text.add(TURN);
        text.add(turnStatus);
        text.add(AI);
        text.add(aiToggle);

    }

    public void refreshText()
    {
        if (board.gameIsWon()!=null) //set victor if there is one
        {
            if (board.gameIsWon().getIsWhite())  
            {    
                victoryStatus.setText("WHITE");
            }
            else
            {
                victoryStatus.setText("RED");
            }
        }
        else
        {
            victoryStatus.setText("???");
        }

        if (board.isWhiteTurn()) //display turn
            turnStatus.setText("WHITE");
        else
            turnStatus.setText("RED");

    }

    private int[] pressed(MouseEvent e) //returns pixel coordinates where clicked
    {

        Component c = boardGUI.findComponentAt(e.getX(), e.getY());

        int[] coordinates = new int[2]; //[x,y]
        coordinates[0] = e.getX();
        coordinates[1] = e.getY();
        return coordinates;
    }

    private int[] arrayCoord(int[] pixelCoord) //returns coordinates within the checkerboard, limited to [0,0] to [7,7]
    {

        for (int i=0; i<2; i++)
            pixelCoord[i] /= MULTIPLIER;        //Divide the pixel by the width of each piece

        return pixelCoord;
    }

    private void move(int[][] currentSelected) //moves the pieces in the Board variable
    {
        board.makeMove(currentSelected[0][1],currentSelected[0][0],currentSelected[1][1],currentSelected[1][0]);
    }

    public static void main (String[] args) //Run the game!
    {
        CheckersGUI gui = new CheckersGUI();
        gui.renderBoard();
    }

}
