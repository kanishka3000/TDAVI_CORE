/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catchaction.ui.componant;

import catchaction.core.SoundSource;

/**
 *
 * @author kanishka
 */
public interface AudioComponant {

    public void setNeedForcus(boolean state);
    public boolean isNeedForcus();

    public void setXP(float x);

    public void setYP(float y);

    public float getXP();

    public float getYP();

    public void setReadText(String readtext);

    public void finalizeAudio();

    public void createWave();

    public SoundSource getAudioComp();

    public void setFocus();
    public void doEnter();
}
