/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catchaction.core;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kanishka
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//        BForm b = new BForm();
//        BComponant bc10 = new BComponant();
//
//        bc10.setTitle("first");
//        bc10.readText="process of assigning phonetic transcriptions to words is called ";
//        b.addToLeft(bc10);
//
//
//        BComponant bc11 = new BComponant();
//
//        bc11.setTitle("first1");
//        b.addToLeft(bc11);
//
//        BComponant bc12 = new BComponant();
//
//        bc12.setTitle("firs2t");
//        b.addToLeft(bc12);
//        BComponant bc2 = new BComponant();
//        bc2.setTitle("second");
//
//        BComponant bc3 = new BComponant();
//        bc3.setTitle("Tree");
//
//
//
//
//        //  b.addToLeft(bc10);
//        b.addToMiddle(bc2);
//        b.addToRight(bc3);
//        b.loadAudio();
//        b.setVisible(true);
//        SoundSource so1 = new SoundSource();
//
//        so1.y=2;
//        so1.x=3;
//        SoundSource so2 = new SoundSource();
//        so2.AudioPath = "C:\\Documents and Settings\\kanishka\\My Documents\\Downloads\\Music\\Windows XP Startup.wav";
//        SoundComposer compose = new SoundComposer();
//        compose.SoundSources.add(so1);
//        compose.SoundSources.add(so2);
//        compose.loadSoundSources();
//        compose.play(so1.SourceID);

//
        SoundCreater wv1 = new SoundCreater();
        wv1.SaveName = "FirstOne";
        wv1.readText = "Synthesized speech can be created by concatenating pieces of recorded. speech that are stored in a database Systems differ in the size of the stored speech units";
        wv1.createWave();
        wv1.x = 0.3f;
        SoundComposer compose = new SoundComposer();
        compose.SoundSources.add(wv1);

        System.out.println(wv1.SourceID);

        SoundCreater wv2 = new SoundCreater();
        wv2.SaveName = "secondone";
        wv2.readText = "The quality of a speech synthesizer is judged by its similarity to the human voice";
        wv2.createWave();
        wv2.y = 0.3f;
        wv2.x = -0.3f;
        compose.SoundSources.add(wv2);
        compose.loadSoundSources();
        compose.play(wv1.SourceID);
        System.out.println("v2");
        compose.play(wv2.SourceID);

        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(i++);
                }
            }
        }).start();

    }
}
