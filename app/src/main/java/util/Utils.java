package util;

import java.util.Calendar;

public class Utils {

    public static String getdateandtime(){

        String getdateandtime = java.text.DateFormat.getDateTimeInstance().format
                (Calendar.getInstance().getTime());
        return getdateandtime;
    }
}
