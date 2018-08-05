package com.example.irfanirfi.customlistview;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by Irfan Irfi on 9/13/2017.
 */

public class NotficationServie extends Service {
    Thread t;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service started",Toast.LENGTH_LONG).show();

       t  = new Thread()
        {

            @Override
            public void run() {
                super.run();
                try {
                    while (true) {
                        sleep(10000);
                        displayNotification();
                    }
                }catch(Exception e)
                {

                }
            }
        };
        t.start();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this,"Service stopped",Toast.LENGTH_LONG).show();

    }

    public void displayNotification()
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.jelly);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(sound);
        builder.setContentTitle("Notification");
        builder.setContentText("some text");
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1,builder.build());
    }
}
