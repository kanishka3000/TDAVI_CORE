/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 *
 * @author kanishka
 */
public class CProperties {

    static CProperties pro = null;
    java.util.Properties property;
    ResourceBundle bunn;

    private CProperties() throws IOException {
        property = new java.util.Properties();
        bunn = ResourceBundle.getBundle("catchaction.core.catchaction");
    }

    public static CProperties getInstance() throws IOException {
        if (pro == null) {
            pro = new CProperties();
        }
        return pro;
    }

    public String getProperty(String property) {
        return bunn.getString(property);


    }
}
