/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catchaction.core;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * @author kanishka
 */
public class BForm extends Frame implements KeyListener {

    static int Left_Colomn = 0;
    static int Middle_Colomn = 1;
    static int Right_Colomn = 2;
    ArrayList<BComponant> LeftList;
    ArrayList<BComponant> MiddleList;
    ArrayList<BComponant> RightList;
    int[] FocussedComponant;
//supports only three colomns
    SoundComposer composer = new SoundComposer();
    String Welcome_Speeach = null;

    @Override
    public void paint(Graphics g) {
    }

    @Override
    public void paintAll(Graphics g) {
    }

    public BForm() {

        this.addKeyListener(this);
        LeftList = new ArrayList<BComponant>();
        MiddleList = new ArrayList<BComponant>();
        RightList = new ArrayList<BComponant>();
        FocussedComponant = new int[2];
        FocussedComponant[0] = -1;//colomn
        FocussedComponant[1] = -1;//row
    }

    public void addToLeft(BComponant componant) {
        if (FocussedComponant[0] == -1) {
            FocussedComponant[0] = Left_Colomn;
            FocussedComponant[1] = 0;
        }
        componant.setColomn(Left_Colomn);
        componant.setRow(LeftList.size());
        componant.x = -0.4f;
        LeftList.add(componant);
        addToComposer(componant);
    }

    public void addToMiddle(BComponant componant) {
        if (FocussedComponant[0] == -1) {
            FocussedComponant[0] = Middle_Colomn;
            FocussedComponant[1] = 0;
        }
        componant.setColomn(Middle_Colomn);
        componant.setRow(MiddleList.size());
        componant.x = 0;
        MiddleList.add(componant);
        addToComposer(componant);
    }

    public void addToRight(BComponant componant) {
        if (FocussedComponant[0] == -1) {
            FocussedComponant[0] = Right_Colomn;
            FocussedComponant[1] = 0;
        }
        componant.setColomn(Right_Colomn);
        componant.setRow(RightList.size());
        componant.x = 1;
        RightList.add(componant);
        addToComposer(componant);
    }

    public void loadAudio() {
        composer.loadSoundSources();
    }

    public BComponant getFocussedComponant() {
        if (FocussedComponant[0] == -1 || FocussedComponant[1] == -1) {
            return null;
        }
        ArrayList<BComponant> list;
        BComponant comp;
        if (FocussedComponant[0] == Left_Colomn) {
            System.out.println("ll");
            list = LeftList;
            comp = list.get(FocussedComponant[1]);
            comp.setColomn(Left_Colomn);
            comp.setTotalRows(list.size());
            comp.setRow(FocussedComponant[1]);
        } else if (FocussedComponant[0] == Middle_Colomn) {
            list = MiddleList;
            System.out.println("ml");
            comp = list.get(FocussedComponant[1]);
            comp.setColomn(Middle_Colomn);
            comp.setTotalRows(list.size());
            comp.setRow(FocussedComponant[1]);
        } else if (FocussedComponant[0] == Right_Colomn) {
            list = RightList;
            comp = list.get(FocussedComponant[1]);
            comp.setColomn(Right_Colomn);
            comp.setTotalRows(list.size());
            comp.setRow(FocussedComponant[1]);
        } else {
            System.out.println("Error null");
            return null;
        }
        //comp = list.get(FocussedComponant[1]);

        System.out.println(list.size());
        System.out.print(FocussedComponant[1]);
        return comp;

    }

    public void moveComp() {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        BComponant comp;
        if (KeyEvent.VK_LEFT == e.getKeyCode()) {
            if (FocussedComponant[0] != -1 && FocussedComponant[0] != Left_Colomn) {
                FocussedComponant[0] = FocussedComponant[0] - 1;
            }
            System.out.println(FocussedComponant[0] + "," + FocussedComponant[1]);
            comp = getFocussedComponant();
            //comp.setAndPlay();
            composer.play(comp.SourceID);
        } else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
            if (FocussedComponant[0] != -1 && FocussedComponant[0] != Right_Colomn) {
                FocussedComponant[0] = FocussedComponant[0] + 1;
                System.out.println(FocussedComponant[0] + "," + FocussedComponant[1]);
                System.out.print(getFocussedComponant());
                comp = getFocussedComponant();
                composer.play(comp.SourceID);
            }
        } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
            if (FocussedComponant[0] != -1 && FocussedComponant[1] != 0) {
                System.out.println("down");
                FocussedComponant[1] = FocussedComponant[1] - 1;
                System.out.println(FocussedComponant[0] + "," + FocussedComponant[1]);
                System.out.print(getFocussedComponant());
                comp = getFocussedComponant();
                // comp.setAndPlay();
            }
        } else if (KeyEvent.VK_UP == e.getKeyCode()) {

            if (FocussedComponant[0] != -1 && FocussedComponant[1] != 0) {
                System.out.println("up");
                FocussedComponant[1] = FocussedComponant[1] + 1;
                System.out.println(FocussedComponant[0] + "," + FocussedComponant[1]);
                System.out.print(getFocussedComponant());
                comp = getFocussedComponant();
                //comp.setAndPlay();
            }
        }


    }

    public void keyReleased(KeyEvent e) {
    }

    public void playAudioOfCurrentComponant() {
        int Colomn;
        int TotalElementsInCurrentList;

    }

    private void addToComposer(BComponant componant) {
        composer.addToSoundSources(componant);
    }
}
