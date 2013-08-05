/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewFrame.java
 *
 * Created on Nov 21, 2010, 10:42:11 AM
 */
package catchaction.ui.componant;

import catchaction.core.ASystem;
import catchaction.core.SoundComposer;
import catchaction.core.SoundCreater;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author kanishka
 */
public class BFrame extends java.awt.Frame implements KeyListener, FocusListener {

    /** Creates new form NewFrame */
    private AudioComponant[][] componants = null;
    private int[] currentpossition;
    private SoundComposer composer;
    public SoundCreater alert;
    public int FrameBasicRiquirement = 0;
    public static final int FRAMEREQUIREMENT_READY = 1;
    public static final int FRAMEREQUIREMENT_DIMENTIONSNOTSET = 0;
    public static final int ONMAINTRAVERSE = 0;
    public static final int ONCONTEXTTRAVERSE = 1;
    public int TraverseState = ONMAINTRAVERSE;
    public String Welcome_Speech = null;
    TraverseData prevdata;
    TraverseData basedata;

    public SoundComposer getComposer() {
        return this.composer;
    }

    public BFrame(int rows, int colms) throws Exception {
        super();
        basedata = new TraverseData(rows, colms);
        currentpossition = new int[2];//row,colomn
        // currentpossition[0] = rows - 1;
        currentpossition[0] = 0;
        currentpossition[1] = 0;

        setDimentions(rows, colms);
        resetTraverse();
        addKeyListener(this);
        addFocusListener(this);
        composer = new SoundComposer();
        alert = new SoundCreater();
        // initComponents();
        setTitle("two dimentional audio");
    }

    public void doEnter() {
    }

    public void alert(String alert) {
        if (alert != null) {
            this.alert.speakLive(alert);
        }
    }

    public void doEscape() {
        System.out.println("escaper");
        if (TraverseState == ONCONTEXTTRAVERSE) {
            System.out.println("enter back to base form");
            basedata = prevdata;
            TraverseState = ONMAINTRAVERSE;
            resetTraverse();
        }
    }

    public void leaveResources() {
        //composer.cleanAudio();
        composer.killALData();
    }

    private void doContext() {
        //handling the menu event for the componant
        if (componants[currentpossition[0]][currentpossition[1]] instanceof BButton && ((BButton) componants[currentpossition[0]][currentpossition[1]]).travser != null) {
            prevdata = basedata;
            basedata = ((BButton) componants[currentpossition[0]][currentpossition[1]]).travser;
            TraverseState = ONCONTEXTTRAVERSE;
            resetTraverse();
        } else {
            System.out.println("warning: contexts are supported only by buttons");
        }
    }

    public void doHome() {
        System.out.println("do Home");
    }

    public void onFormFocus() {
        //throw new UnsupportedOperationException("Not supported yet.");
        alert("on base form");
    }

    private void interuptGlimpse() {
        if(runner!=null&&runner.isAlive()){
            runner.stop();
            System.out.println("interrupted the form description");
        }
        
    }

    private void resetTraverse() {
        componants = basedata.componants;
        currentpossition = basedata.currentpossition;
    }

    private void setDimentions(int rows, int colomns) throws Exception {
        //rows and colomns are 0 based;
        //componants = new AudioComponant[rows][colomns];
        if (rows > 0 && colomns > 0) {
            this.FrameBasicRiquirement = BFrame.FRAMEREQUIREMENT_READY;
            System.out.println("ready");
        } else {
            throw new Exception("cant' be 0");
        }
    }

    public void add(AudioComponant componant, int row, int colomn) throws Exception {
        if (this.FrameBasicRiquirement != BFrame.FRAMEREQUIREMENT_READY) {
            throw new Exception("Frame dimentions are not set");
        }
        if (row > componants.length || colomn > componants[0].length) {
            throw new ArrayIndexOutOfBoundsException("rows or coloms are too large");
        }
        componants[row][colomn] = componant;
        // will this casting work?yes
        //componant.createWave();
        composer.addToSoundSources(componant.getAudioComp());
        this.add((Component) componant);
        if (componant instanceof BButton && ((BButton) componant).travser != null) {
            AudioComponant[][] children = ((BButton) componant).travser.componants;
            for (AudioComponant[] com : children) {
                AudioComponant realcom = com[0];
                composer.addToSoundSources(realcom.getAudioComp());
                this.add((Component) realcom);
            }
        }
    }

    @Override
    public void setVisible(boolean fa) {
        super.setVisible(fa);
        if (fa) {
            if (Welcome_Speech != null) {
                BLabel b = new BLabel();
                b.audioComp.speakLive(Welcome_Speech);
            }

            composer.loadSoundSources();
        } else {
            alert("please wait");
            leaveResources();
        }
    }
    Thread runner;

    public void glipseForm() {
        runner = new Thread(new Runnable() {

            public void run() {
                AudioComponant prevcomp = null;
                for (int currow = componants.length - 1; currow >= 0; currow--) {
                    for (int curcol = 0; curcol < componants[currow].length; curcol++) {
                        AudioComponant compont = componants[currow][curcol];
                        if (prevcomp != null) {
                            composer.stop(prevcomp.getAudioComp().SourceID);
                        }
                        composer.play(compont.getAudioComp().SourceID);
                       // System.out.println("auto read");
                        prevcomp = compont;
                        try {
                            Thread.sleep(1000 * 3);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        runner.start();

    }

    public void revalidateAudioPosition() {

        int totalrows = componants.length;
        int totaolcolms = componants[0].length;
        int halrow = (int) Math.floor(totalrows / 2);
        int halcol = (int) Math.floor(totaolcolms / 2);
        for (int currow = 0; currow < componants.length; currow++) {
            for (int curcol = 0; curcol < componants[currow].length; curcol++) {
                //System.out.println(currow + "___" + curcol);
                // set the x and y of the actual componant. this method should be called once the componant placement changes
                AudioComponant compont = componants[currow][curcol];
                //System.out.println(Double.toString(currow - halrow) + "---" + Double.toString(curcol - halcol));
                compont.setYP(currow - halrow);
                compont.setXP(curcol - halcol);
                if (compont instanceof BButton && ((BButton) compont).travser != null) {
                }
            }
        }
    }

    public AudioComponant getCurrentComponant() {
        return componants[currentpossition[0]][currentpossition[1]];
    }

    private void expressComponant() {
        //System.out.println(componants[currentpossition[0]][currentpossition[1]].getXP() + ",,,," + componants[currentpossition[0]][currentpossition[1]].getYP());
        composer.play(componants[currentpossition[0]][currentpossition[1]].getAudioComp().SourceID);
        if (componants[currentpossition[0]][currentpossition[1]].isNeedForcus()) {
            //componants[currentpossition[0]][currentpossition[1]].setFocus();
        } else {
        }
    }

    private void expressComponantFocus() {
        //System.out.println(componants[currentpossition[0]][currentpossition[1]].getXP() + ",,,," + componants[currentpossition[0]][currentpossition[1]].getYP());
        composer.play(componants[currentpossition[0]][currentpossition[1]].getAudioComp().SourceID);
        if (componants[currentpossition[0]][currentpossition[1]].isNeedForcus()) {
            componants[currentpossition[0]][currentpossition[1]].setFocus();
        } else {
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new java.awt.GridLayout(1, 0));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     */
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        //System.out.println("key");
        if (!ASystem.isIsLinear()) {
            // if the arrow keys make it positional
            if (KeyEvent.VK_LEFT == e.getKeyCode()) {
                //System.out.println("left");
                if (currentpossition[1] > 0) {
                    currentpossition[1]--;
                    expressComponant();
                } else {

                    composer.playRandomPositioned(getCurrentComponant().getXP() - 1, getCurrentComponant().getYP());
                }
            } else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
                //System.out.println("right");
                if (currentpossition[1] < componants[0].length - 1) {
                    currentpossition[1]++;
                    expressComponant();
                } else {
                    composer.playRandomPositioned(getCurrentComponant().getXP() + 1, getCurrentComponant().getYP());
                }
            } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
                if (currentpossition[0] > 0) {
                    currentpossition[0]--;
                    expressComponant();
                } else {
                    composer.playRandomPositioned(getCurrentComponant().getXP(), getCurrentComponant().getYP() - 1);
                }
            } else if (KeyEvent.VK_UP == e.getKeyCode()) {
                if (currentpossition[0] < componants.length - 1) {
                    currentpossition[0]++;
                    expressComponant();
                } else {
                    composer.playRandomPositioned(getCurrentComponant().getXP(), getCurrentComponant().getYP() + 1);
                }
            }
        } else {
            // if its liners the top and bottom arrow keys should work.

            if (KeyEvent.VK_DOWN == e.getKeyCode()) {
                if (currentpossition[1] < componants[0].length - 1) {
                    currentpossition[1]++;
                    expressComponant();
                } else {
                    if (currentpossition[0] < componants.length - 1) {
                        currentpossition[1] = 0;
                        currentpossition[0]++;
                        expressComponant();
                    } else {
                        composer.playRandomPositioned(getCurrentComponant().getXP(), getCurrentComponant().getYP() + 1);
                        //System.out.println("out of bound");
                    }
                }
                //System.out.println(currentpossition[0] + "-" + currentpossition[1] + "|||");


            } else if (KeyEvent.VK_UP == e.getKeyCode()) {

                if (currentpossition[1] > 0) {
                    currentpossition[1]--;
                    expressComponant();
                } else {
                    if (currentpossition[0] > 0) {
                        currentpossition[1] = componants[0].length - 1;
                        currentpossition[0]--;
                        expressComponant();
                    }else{
                    composer.playRandomPositioned(getCurrentComponant().getXP(), getCurrentComponant().getYP() + 1);
                    }

                }

                //System.out.println(currentpossition[0] + "-" + currentpossition[1] + "|||");

            }
        }
        if (KeyEvent.VK_ENTER == e.getKeyCode()) {
            doEnter();

        } else if (KeyEvent.VK_ESCAPE == e.getKeyCode()) {
            doEscape();
        } else if (KeyEvent.VK_F1 == e.getKeyCode()) {
            glipseForm();

        } else if (KeyEvent.VK_F2 == e.getKeyCode()) {
            //System.out.println("Enter context");
            alert("please select an option");
            doContext();
        } else if (KeyEvent.VK_HOME == e.getKeyCode()) {
            doHome();
        } else if (KeyEvent.VK_F3 == e.getKeyCode()) {
            expressComponantFocus();
        } else if (KeyEvent.VK_CONTROL == e.getKeyCode()) {
            interuptGlimpse();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void focusGained(FocusEvent e) {
        // onFormFocus();
    }

    public void focusLost(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
