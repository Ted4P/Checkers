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
    public Board board;
    private JLabel[][] GUIboard;

    private JPanel boardGUI;

    private boolean selected = false;

    private int[][] currentSelected;

    private JLabel boardBackground;

    private final int BASELINE = 42;
    private final int MULTIPLIER = 62;

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
        boardGUI.setLayout(new GridLayout(8,8));
        boardGUI.addMouseListener(new MouseAdapter() {

                int selected =0;

                public void mouseClicked(MouseEvent e) {

                    if (selected==0)
                    {
                        currentSelected[0]=arrayCoord(pressed(e));
                        selected++;
                        if(!board.isValidSelection(currentSelected[0][1], currentSelected[0][0])){
                            System.out.println("INVALID MOVE");
                            currentSelected = new int[2][2];
                            selected=0;
                        }
                    }
                    else if (selected ==1)
                    {
                        currentSelected[1]=arrayCoord(pressed(e));
                        move(currentSelected);
                        System.out.println("MOVE");
                        board.printArr();

                        renderBoard();
                        System.out.println("RENDER BOARD");

                        currentSelected = new int[2][2];
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

    public void renderBoard()
    {

        boolean previousColorIsWhite = false;

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (board.getPiece(i,j) != null)
                {
                    if (board.getPiece(i,j).getIsWhite())
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
                else //if no piece, then blank tile
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

        getContentPane().add(panel);

        //boardGUI.addMouseListener(this);

        pack();
        this.setVisible(true);

    }

    private int[] pressed(MouseEvent e)
    {

        Component c = boardGUI.findComponentAt(e.getX(), e.getY());

        int[] coordinates = new int[2]; //[x,y]

        System.out.println(e.getX() + "," + e.getY());

        coordinates[0] = e.getX();
        coordinates[1] = e.getY();
        return coordinates; 
    }

    private int[] arrayCoord(int[] pixelCoord)
    {
        int[] newArr = new int[2];

        for (int i=0; i<2; i++)
            newArr[i] = pixelCoord[i]/MULTIPLIER;

        return newArr;
    }

    private void move(int[][] currentSelected)
    {
        board.makeMove(currentSelected[0][1],currentSelected[0][0],currentSelected[1][1],currentSelected[1][0]);

        System.out.println(currentSelected[0][0]+","+currentSelected[0][1] + " to " + currentSelected[1][0] + "," + currentSelected[1][1]);
    }

    public static void tester (String[] args)
    {
        CheckersGUI gui = new CheckersGUI();

        gui.board.makeMove(2,0,3,1);
        gui.board.printArr();

        gui.renderBoard();

    }

}
