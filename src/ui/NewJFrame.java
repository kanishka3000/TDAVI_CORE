/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJFrame.java
 *
 * Created on Oct 27, 2010, 9:34:55 AM
 */
package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author kanishka
 */
public class NewJFrame extends javax.swing.JFrame implements KeyListener {

    /** Creates new form NewJFrame */
    public NewJFrame() {
        initComponents();
        addKeyListener(this);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout());

        jButton1.setText("jButton1");
        getContentPane().add(jButton1);

        jButton2.setText("jButton2");
        getContentPane().add(jButton2);

        jTextField1.setText("jTextField1");
        getContentPane().add(jTextField1);

        jButton3.setText("jButton3");
        getContentPane().add(jButton3);

        jButton4.setText("jButton4");
        getContentPane().add(jButton4);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("once");
        if (KeyEvent.VK_LEFT == e.getKeyCode()) {
            System.out.println("left");
            jButton1.requestFocusInWindow();
        } else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
            System.out.println("right");
            jButton3.requestFocusInWindow();
        } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
        } else if (KeyEvent.VK_UP == e.getKeyCode()) {
        }
    }

    public void keyReleased(KeyEvent e) {
        
    }
}