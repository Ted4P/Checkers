/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author hyunchoi98
 */
public class CheckersGUI extends javax.swing.JFrame  {
    //keeps track of a Board, a 2d array of JLabels to represent each tile, and JPanel to store the tiles
    public Board board; 
    private JLabel[][] GUIboard;

    private JPanel boardGUI;

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
                            System.out.println("INVALID MOVE");
                            currentSelected = new int[2][2];
                            selected=0;
                        }
                    }
                    else if (selected ==1) //target tile
                    {
                        currentSelected[1]=arrayCoord(pressed(e));
                        move(currentSelected);
                        System.out.println("MOVE");
                        board.printArr();

                        if (board.gameIsWon()!=null)
                        {

                        }
                        renderBoard();
                        System.out.println("RENDER BOARD");

                        currentSelected = new int[2][2]; //revert
                        selected=0;
                    }

                    //debugging

                    for (int[] curr: currentSelected)
                    {
                        for (int j: curr)
                            System.out.print(j+" ");
                        System.out.println();
                    }
                    System.out.println();

                }

            });
        //do this last
        renderBoard();

    }

    publ

    ic void renderBoard() //method to arrange images to form the board
    {

        boolean previousColorIsWhite = false; //for arrangement

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (board.getPiece(i,j) != null)
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

        JPanel panel = new JPanel(); //enclose GridLayout within JPanel on the JFrame
        panel.add(boardGUI);
        panel.setLocation(42,42);

        getContentPane().add(panel); //add panel to window
        this.setResizable(false);

        this.setVisible(true);//make it visible
        this.setContentPane(panel);
        this.pack();

    }

    priv

    ate int[] pressed(MouseEvent e) //method to return pixel coordinates
    {

        Component c = boardGUI.findComponentAt(e.getX(), e.getY());

        int[] coordinates = new int[2]; //[x,y]

        System.out.println(e.getX() + "," + e.getY()); //debug

        coordinates[0] = e.getX();
        coordinates[1] = e.getY();
        return coordinates;
    }

    priv

    ate int[] arrayCoord(int[] pixelCoord) //method to return coordinates within the checkerboard, limited to [0,0] to [7,7]
    {

        for (int i=0; i<2; i++)
            pixelCoord[i] /= MULTIPLIER;

        return pixelCoord;
    }

    priv

    ate void move(int[][] currentSelected) //moves the pieces in the Board variable
    {
        board.makeMove(currentSelected[0][1],currentSelected[0][0],currentSelected[1][1],currentSelected[1][0]);

        System.out.println(currentSelected[0][0]+","+currentSelected[0][1] + " to " + currentSelected[1][0] + "," + currentSelected[1][1]);
    }

    publ

    ic static void run () //runs the game with debugging console
    {
        CheckersGUI gui = new CheckersGUI();
        gui.board.printArr();
        gui.renderBoard();

    }

}
