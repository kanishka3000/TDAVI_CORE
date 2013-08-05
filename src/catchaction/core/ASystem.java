/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catchaction.core;

/**
 *
 * @author kanishka
 */
public class ASystem {

    private static boolean isLinear = false;

    public static void systemPositional(boolean status) {
        SoundComposer.isPositional = status;
    }

    /**
     * @return the isLinear
     */
    public static boolean isIsLinear() {
        return isLinear;
    }

    /**
     * @param aIsLinear the isLinear to set
     */
    public static void setIsLinear(boolean aIsLinear) {
        isLinear = aIsLinear;
    }
    public void playBusyTone(){

    }

}
