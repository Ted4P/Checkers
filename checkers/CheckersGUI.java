/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JLabel;

/**
 *
 * @author hyunchoi98
 */
public class CheckersGUI extends javax.swing.JFrame {

    /**
     * Creates new form CheckersGUI
     */
    public CheckersGUI() {
        initComponents();
        board = new Board();
        //red = new JLabel[12];
        //white = new JLabel[12];
    }

    /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        //white
        //for (int i=0; i<12; i++)
            //white[i] = new JLabel();

        /** jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        jLabel13 = new JLabel();
                 */

        //red
        //for (int k=0; k<12; k++)
            //red[k] = new JLabel();

        /** jLabel15 = new JLabel();
        jLabel16 = new JLabel();
        jLabel17 = new JLabel();
        jLabel18 = new JLabel();
        jLabel19 = new JLabel();
        jLabel20 = new JLabel();
        jLabel21 = new JLabel();
        jLabel22 = new JLabel();
        jLabel23 = new JLabel();
        jLabel24 = new JLabel();
        jLabel25 = new JLabel();
        jLabel26 = new JLabel();
                 */

        //board
        boardBackground = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        /**

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/white.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(42, 40, 96, 43);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/white.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(166, 40, 96, 43);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/white.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(290, 40, 96, 43);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/white.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(416, 40, 96, 43);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/white.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(104, 102, 52, 43);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/white.png"))); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(228, 102, 96, 43);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/white.png"))); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(352, 102, 96, 43);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/white.png"))); // NOI18N
        getContentPane().add(jLabel9);
        jLabel9.setBounds(480, 102, 96, 43);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/white.png"))); // NOI18N
        getContentPane().add(jLabel10);
        jLabel10.setBounds(42, 166, 96, 43);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/white.png"))); // NOI18N
        getContentPane().add(jLabel11);
        jLabel11.setBounds(166, 166, 96, 43);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/white.png"))); // NOI18N
        getContentPane().add(jLabel12);
        jLabel12.setBounds(290, 166, 96, 43);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/white.png"))); // NOI18N
        getContentPane().add(jLabel13);
        jLabel13.setBounds(416, 166, 52, 43);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red.png"))); // NOI18N
        getContentPane().add(jLabel15);
        jLabel15.setBounds(104, 354, 96, 42);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red.png"))); // NOI18N
        getContentPane().add(jLabel16);
        jLabel16.setBounds(228, 354, 96, 42);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red.png"))); // NOI18N
        getContentPane().add(jLabel17);
        jLabel17.setBounds(352, 354, 96, 42);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red.png"))); // NOI18N
        getContentPane().add(jLabel18);
        jLabel18.setBounds(480, 354, 96, 42);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red.png"))); // NOI18N
        getContentPane().add(jLabel19);
        jLabel19.setBounds(42, 416, 96, 42);

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red.png"))); // NOI18N
        getContentPane().add(jLabel20);
        jLabel20.setBounds(166, 416, 96, 42);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red.png"))); // NOI18N
        getContentPane().add(jLabel21);
        jLabel21.setBounds(290, 416, 96, 42);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red.png"))); // NOI18N
        getContentPane().add(jLabel22);
        jLabel22.setBounds(416, 416, 96, 42);

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red.png"))); // NOI18N
        getContentPane().add(jLabel23);
        jLabel23.setBounds(104, 480, 96, 42);

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red.png"))); // NOI18N
        getContentPane().add(jLabel24);
        jLabel24.setBounds(228, 480, 96, 42);

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red.png"))); // NOI18N
        getContentPane().add(jLabel25);
        jLabel25.setBounds2, 480, 96, 42);

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red.png"))); // NOI18N
        getContentPane().add(jLabel26);
        jLabel26.setBounds(480, 480, 96, 42);

                 */

        boardBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/board.png"))); // NOI18N
        getContentPane().add(boardBackground);
        boardBackground.setBounds(32, 30, 502, 500);

        renderBoard();
        
        
        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void renderBoard()
    {

        for (int i = 0; i < 8; i++)
        {
            for (int j=0; j < 8; j++)
            {
                if(board.getPiece(i,j) != null)
                    GUIboard[i][j] = new JLabel();
                
            }
        }

        
        for (int i = 0; i < 8; i++)
        {
            for (int j=0; j < 8; j++)
            {
                if(GUIboard[i][j] != null)
                {
                    if (board.getPiece(i,j).getIsWhite())
                    {
                        GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/white.png")));
                        getContentPane().add(GUIboard[i][j]);
                        GUIboard[i][j].setBounds(BASELINE+MULTIPLIER*i, BASELINE+MULTIPLIER*j, 96, 42);
                    }
                    else 
                    {
                        GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red.png")));
                        getContentPane().add(GUIboard[i][j]);
                        GUIboard[i][j].setBounds(BASELINE+MULTIPLIER*i, BASELINE+MULTIPLIER*j, 96, 42);
                    }
                }
                
                

                                

            }
        }
        
        
        
        
    }

    /**
          * @param args the command line arguments
          */
    public static void main(String args[]) {
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

    private Board board;
    private JLabel[][] GUIboard;
    //private JLabel[] white;
    //private JLabel[] red;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel boardBackground;

    private final int BASELINE = 42;
    private final int MULTIPLIER = 62; 

    /** private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel17;
    private JLabel jLabel18;
    private JLabel jLabel19;
    private JLabel jLabel2;
    private JLabel jLabel20;
    private JLabel jLabel21;
    private JLabel jLabel22;
    private JLabel jLabel23;
    private JLabel jLabel24;
    private JLabel jLabel25;
    private JLabel jLabel26;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
         */
    // End of variables declaration//GEN-END:variables
}
