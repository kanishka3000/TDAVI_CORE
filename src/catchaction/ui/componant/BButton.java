/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catchaction.ui.componant;

import catchaction.core.SoundSource;
import catchaction.core.SoundCreater;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;

/**
 *
 * @author kanishka
 */
public class BButton extends JButton implements KeyListener, AudioComponant {

    public String DescriptiveText = "This is the Default Text";
    public SoundCreater audioComp = new SoundCreater();
    boolean NeedFocus = false;
    TraverseData travser = null;

    public void doEnter() {
    }

    public TraverseData getTraverse() {
        return travser;
    }

    public BButton() {
        super();
        audioComp.SaveName = Integer.toString(this.hashCode());

    }

    public BButton(int rows) {
        super();
        if (rows < 1) {
            System.out.println("Error, number of rows is invalid");
        }

        audioComp.SaveName = Integer.toString(this.hashCode());
        travser = new TraverseData(rows, 1);

    }
    /*
    These methods are for the Audio componant. Since muliple inheritance is not supported,
     */

    public void add(AudioComponant componant, int row, int one) throws Exception {
        /*
        child componants, later the system should add them to the main form
         */
        int totaolrows = this.travser.componants.length / 2;
        if (row > this.travser.componants.length - 1) {
            System.out.println("Error in adding child");
        }
        componant.setXP(0);
        componant.setYP(row - totaolrows);
        System.out.println("button at:" + (row - totaolrows));
        travser.add(componant, row, 0);
    }

    public SoundSource getAudioComp() {
        return this.audioComp;
    }

    public void setX(float x) {
        audioComp.x = x;
    }

    public void setReadText(String readtext) {
        audioComp.readText = readtext;
    }

    public void finalizeAudio() {
        audioComp.createWave();
    }

    /*
    These methods are for the Audio componant. Since muliple inheritance is not supported,
     */
    public void keyPressed(KeyEvent e) {
        //implementation of all the
        e.getComponent().getParent().requestFocus();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void setY(float y) {
        audioComp.y = y;
    }

    public String toString() {
        return this.audioComp.x + "," + this.audioComp.y;
    }

    public void setXP(float x) {
        this.audioComp.x = x;
    }

    public void setYP(float y) {
        this.audioComp.y = y;
    }

    public float getXP() {
        return audioComp.x;
    }

    public float getYP() {
        return audioComp.y;
    }

    public void createWave() {
        this.audioComp.createWave();
    }

    public void setNeedForcus(boolean b) {
        this.NeedFocus = b;
    }

    public boolean isNeedForcus() {
        return this.NeedFocus;
    }

    public void setFocus() {
        this.setFocus();
    }
}
