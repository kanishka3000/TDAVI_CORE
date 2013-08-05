/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catchaction.ui.componant;

import catchaction.core.SoundCreater;
import catchaction.core.SoundSource;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;

/**
 *
 * @author kanishka_
 */
public class BLabel extends JLabel implements KeyListener, AudioComponant {

    public String DescriptiveText = "This is the Default Text";
    public SoundCreater audioComp = new SoundCreater();
    boolean NeedFocus = false;
    boolean LongCharBreak = false;

    public BLabel() {
        super();
        audioComp.SaveName = Integer.toString(this.hashCode());
    }

    public void setNumberSeparated(boolean status) {
        
       if(audioComp.readText!=null && status==true){
           audioComp.readText=audioComp.readText.replaceAll("", " ");
       }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void setNeedForcus(boolean state) {
    }

    public boolean isNeedForcus() {
        return false;
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

    public void setReadText(String readtext) {
        audioComp.readText = readtext;
    }

    public void finalizeAudio() {
        audioComp.createWave();
    }

    public void createWave() {
        this.audioComp.createWave();
    }

    public SoundSource getAudioComp() {
        return this.audioComp;
    }

    public void setFocus() {
        this.setFocus();
    }

    public void doEnter() {
    }
}
