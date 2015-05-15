/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 *
 * @author hyunchoi98
 */
public class CheckersGUI extends javax.swing.JFrame {
    public Board board;
    private JLabel[][] GUIboard;

    private JPanel boardGUI;

    private Piece currentSelected;
    //private JLabel[] white;
    //private JLabel[] red;
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
            for (int j=0; j < 8; j++)
            {
                //if(board.getPiece(i,j) != null)
                GUIboard[i][j] = new JLabel();

            }
        }

        boardGUI = new JPanel();
        boardGUI.setLayout(new GridLayout(8,8));

        //do this last
        //renderBoard();

        
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

                /**
                getContentPane().add(GUIboard[i][j]);
                System.out.println("printed ["+i+"]["+j+"]");
                GUIboard[i][j].setBounds(BASELINE+MULTIPLIER*j, BASELINE+MULTIPLIER*i, 96, 42);
                GUIboard[i][j].setBorder(javax.swing.BorderFactory.createEmptyBorder());
                 */

                boardGUI.add(GUIboard[i][j]);

            }
            previousColorIsWhite=!previousColorIsWhite;
        }

        JPanel panel = new JPanel(); //enclose GridLayout within JPanel on the JFrame
        panel.add(boardGUI);
        panel.setLocation(42,42);

        getContentPane().add(panel);
        getContentPane().setVisible(true);
        this.setVisible(true);


        
    }

    /**
     * @param args the command line arguments
     */
    public void main() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CheckersGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckersGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckersGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckersGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new CheckersGUI().setVisible(true);
                }
            });
    }

    public static void tester (String[] args)
    {
        CheckersGUI gui = new CheckersGUI();
        

        gui.board.makeMove(2,0,3,1);
        gui.board.printArr();
        
        gui.renderBoard();
        

        

    }

}
