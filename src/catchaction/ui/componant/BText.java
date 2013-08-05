/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catchaction.ui.componant;

import catchaction.core.SoundSource;
import catchaction.core.SoundCreater;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

/**
 *
 * @author kanishka
 */
public class BText extends JTextField implements KeyListener, AudioComponant, FocusListener {

    public String DescriptiveText = "This is the Default Text";
    public SoundCreater audioComp = new SoundCreater();
    boolean NeedFocus = true;
    public boolean isPassword = false;
    private boolean isLongCharBreak = false;

    public BText() {
        super();
        this.setNeedForcus(true);
        this.addKeyListener(this);
        this.addFocusListener(this);
        audioComp.SaveName = Integer.toString(this.hashCode());
        System.out.println(audioComp.SaveName);

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
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println(getText());
            e.getComponent().getParent().requestFocus();
            return;
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && getText().length() > 0) {
            audioComp.speakLive(getText().substring(getText().length() - 1));
        } else if (!isPassword) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                //System.out.println(testString.substring(testString.lastIndexOf(" ",testString.length()-2)+1, testString.length()-1));
                audioComp.speakLive(getText().substring(getText().lastIndexOf(" ", getText().length() - 2) + 1, getText().length()));
            } else {
                audioComp.speakLive(String.valueOf(e.getKeyChar()));
            }
        } else {
            audioComp.speakLive("star");
        }
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

        if (requestFocus(true)) {
            System.out.println("focus enabled");
        } else {
            System.out.println("no focus");
        }

    }

    public void doEnter() {
    }

    public void focusGained(FocusEvent e) {
//        if (getText() != null || getText().equals("")) {
//            if (!isPassword) {
//            audioComp.speakLive("Currently contains "+getText());
//            }else{
//            audioComp.speakLive("pass word is entered, it has "+getText().length()+" charactors");
//            }
//
//        }
    }

    public void focusLost(FocusEvent e) {
        if (!isPassword) {
            if (!isLongCharBreak) {
                audioComp.speakLive(getText());
            } else {
                audioComp.speakLive(getText().replaceAll("", " "));
            }
        } else {
            audioComp.speakLive("password entered " + getText().length() + " characters");
        }
    }

    /**
     * @return the isLongCharBreak
     */
    public boolean isIsLongCharBreak() {
        return isLongCharBreak;
    }

    /**
     * @param isLongCharBreak the isLongCharBreak to set
     */
    public void setIsLongCharBreak(boolean isLongCharBreak) {
        this.isLongCharBreak = isLongCharBreak;
    }
}
