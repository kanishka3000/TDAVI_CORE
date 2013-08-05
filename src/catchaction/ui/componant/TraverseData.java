/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catchaction.ui.componant;

import catchaction.core.ASystem;

/**
 *
 * @author kanishka_
 */
public class TraverseData {

    public static final int STATUS_OPEN = 1;
    public static final int STATUS_CLOSED = 2;
    AudioComponant[][] componants;
    int[] currentpossition = new int[2];

    public TraverseData(int rows, int colomns) {
        if (!ASystem.isIsLinear()) {
            currentpossition[0] = rows - 1;
        } else {
            currentpossition[0] = 0;
        }
        currentpossition[1] = 0;
        componants = new AudioComponant[rows][colomns];
    }

    public void add(AudioComponant componant, int row, int colomn) throws Exception {
        componants[row][colomn] = componant;

    }

    public AudioComponant getCurrentComponant() {
        return componants[currentpossition[0]][currentpossition[1]];
    }
}
