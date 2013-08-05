/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import catchaction.ui.componant.BButton;
import catchaction.ui.componant.BFrame;
import catchaction.ui.componant.BLabel;
import catchaction.ui.componant.BText;

/**
 *
 * @author kanishka
 */
public class TestFrame extends BFrame {

    @Override
    public void doEnter(){
    getComposer().playRandomPositioned(3.0f, 0.0f);
    }


    public TestFrame(int rows, int cols) throws Exception {
        super(rows, cols);
        //pupulateEle1();
        BButton button1 = new BButton(3);
        button1.setReadText("testing ones");
        BLabel into = new BLabel();
        into.setReadText("in to one");
        button1.add(into, 0, 0);
        BButton button2 = new BButton();
        button2.setReadText("testing twice");
        BLabel into2 = new BLabel();
        into2.setReadText("into two");
        button1.add(into2, 1, 0);
        BText btext=new BText();
        btext.setReadText("Text testing");
        button1.add(btext,2,0);

        BButton button3 = new BButton();
        button3.setReadText("testing ");
        BButton button4 = new BButton();
        button4.setReadText("testing ");
        BButton button5 = new BButton();
        button5.setReadText("testing ");
        BButton button6 = new BButton();
        button6.setReadText("testing ");
        BButton button7 = new BButton();
        button7.setReadText("testing ");
        BButton button8 = new BButton();
        button8.setReadText("testing");
        BButton button9 = new BButton();
        button9.setReadText("testing");



        BButton button10 = new BButton();
        button10.setReadText("testing ");
        BButton button11 = new BButton();
        button11.setReadText("testing ");
        BButton button12 = new BButton();
        button12.setReadText("testing ");
        BButton button13 = new BButton();
        button13.setReadText("testing ");
        BButton button14 = new BButton();
        button14.setReadText("testing ");
        BButton button15 = new BButton();
        button15.setReadText("testing ");

        add(button1, 0, 0);
        add(button2, 0, 1);
        add(button3, 0, 2);
        add(button4, 1, 0);
        add(button5, 1, 1);


        add(button6, 1, 2);
        add(button7, 2, 0);
        add(button8, 2, 1);
        add(button9, 2, 2);
        add(button10, 3, 0);


        add(button11, 3, 1);
        add(button12, 3, 2);
        add(button13, 4, 0);
        add(button14, 4, 1);
        add(button15, 4, 2);

        this.revalidateAudioPosition();

    }

    public static void main(String args[]) {
        TestFrame frame;
        try {
            frame = new TestFrame(5, 3);
            frame.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }




    }

    private void pupulateEle1() throws Exception {

        //populate 3x3
        BButton button1 = new BButton();
        button1.setReadText("university of colombo ");
        BButton button2 = new BButton();
        button2.setReadText("university of colombo ");
        BButton button3 = new BButton();
        button3.setReadText("university of colombo ");
        BButton button4 = new BButton();
        button4.setReadText("university of colombo ");
        BButton button5 = new BButton();
        button5.setReadText("university of colombo ");
        BButton button6 = new BButton();
        button6.setReadText("university of colombo ");
        BButton button7 = new BButton();
        button7.setReadText("university of colombo ");
        BButton button8 = new BButton();
        button8.setReadText("university of colombo ");
        BButton button9 = new BButton();
        button9.setReadText("Robert, I can't find any information on the file");
        add(button1, 0, 0);
        add(button2, 0, 1);
        add(button3, 0, 2);
        add(button4, 1, 0);
        add(button5, 1, 1);
        add(button6, 1, 2);
        add(button7, 2, 0);
        add(button8, 2, 1);
        add(button9, 2, 2);
    }
}
