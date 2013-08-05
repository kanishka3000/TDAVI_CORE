/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catchaction.ui.componant;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Kanishka_
 */
public class BList implements KeyListener{

    AudioComponant[] Childrean;
    float x = 0.0f;
    float y = 0.0f;

    public BList(int rows) {
        Childrean = new AudioComponant[rows];
    }

    public void add(AudioComponant componant, int y) {
        if (y < 0 || y < Childrean.length) {
            throw new IllegalArgumentException("Exceeds the number of children");
        }
        Childrean[y] = componant;
    }

    public void revalidateAudio() {
        int mid_row = Childrean.length / 2;
        int i = 0;
        for (AudioComponant com : Childrean) {
            com.setYP(i - mid_row + this.y);
            com.setXP(this.x);
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
         if (KeyEvent.VK_LEFT == e.getKeyCode()) {
            System.out.println("left");

        } else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {

        } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {


        } else if (KeyEvent.VK_UP == e.getKeyCode()) {

        }
    }

    public void keyReleased(KeyEvent e) {
       
    }
}
