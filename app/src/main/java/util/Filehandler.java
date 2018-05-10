package util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class Filehandler {

    public boolean isExternalStorageAvailable() {

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public String generatefilenameonexternalstorage(String number){

        String pointtofile = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                "Callrecordings" + File.separator + number + "," + Utils.getdateandtime() + ".mp3";

        return pointtofile;
    }

    public String generatefilenameoninternalstorage(Context context,String number){

        String pointtofile = context.getFilesDir().getAbsolutePath() + File.separator +
                             "Callrecordings" + File.separator + number + "," + Utils.getdateandtime() + ".mp3";
        return pointtofile;
    }

    public static String generatebackupfileoninternalstorage(Context context,String number){

        String pointtofile = context.getFilesDir().getAbsolutePath() + File.separator + "backup.mp3";

        return pointtofile;
    }
}
