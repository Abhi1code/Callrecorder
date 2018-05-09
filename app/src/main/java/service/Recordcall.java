package service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import util.Filehandler;

public class Recordcall extends Service {

    private MediaRecorder mediaRecorder;
    private static String number = "";
    private static File pathtosave;
    private util.Filehandler filehandler = new util.Filehandler();
    private static boolean status = false;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getStringExtra("number") != null){
            number = intent.getStringExtra("number");
        }
        startrecording(this);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stoprecording(this);
    }

    private void preparemediarecorder(Context context){

        if (filehandler.isExternalStorageAvailable()){
            pathtosave = filehandler.generatefilenameonexternalstorage();
//            pathtosave = String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+
//                    "record.3gp");
        }else {
            pathtosave = filehandler.generatetempbackupfileinanternalstorage(context);
        }

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(String.valueOf(pathtosave));
        Toast.makeText(context, ""+pathtosave, Toast.LENGTH_LONG).show();
    }

    private void startrecording(Context context) {
        preparemediarecorder(this);
        if (mediaRecorder != null && status == false) {
            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
                status = true;
                Toast.makeText(context, "Recording Started", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "Unable to record call ", Toast.LENGTH_SHORT).show();
            }
        }else {
            mediaRecorder = null;
            Toast.makeText(context, "Unable to record call ", Toast.LENGTH_SHORT).show();
        }
    }

    private void stoprecording(Context context){
        if (mediaRecorder != null && status == true){
            mediaRecorder.stop();
            mediaRecorder.release();
            status = false;
            Toast.makeText(context, "Recording stopped", Toast.LENGTH_SHORT).show();
        }
    }
}
