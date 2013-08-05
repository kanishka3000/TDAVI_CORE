/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author kanishka
 */
public class BLog {
 public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    private BLog() {
    }

    public BLog getInstance() {
        return log;
    }
    BLog log = new BLog();
    private String FileName = "defaultfile";

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String aFileName) {
        FileName = aFileName;
    }

    public static void writeLog(String msg)  {
   
  Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
System.out.println(msg+ "at:"+sdf.format(cal.getTime()));

    }
}
