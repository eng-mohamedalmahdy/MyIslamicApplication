package com.example.myisamlicapplication;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;

public class IslamicApplication extends Application {
    private static final String CHANNEL_ID = "AZAN_CHANNEL";
    private static final String CHANNEL_NAME = "azan channel";

    @Override
    public void onCreate() {
        super.onCreate();

        NotificationManager manager = (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Uri azanSound = Uri.parse("android.resource://" + getApplicationContext().getPackageName()
                + "/" + R.raw.azan);

        createNotificationChannel(manager, azanSound);

    }

    private void createNotificationChannel(NotificationManager manager, Uri sound) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(sound, Notification.AUDIO_ATTRIBUTES_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }

}
