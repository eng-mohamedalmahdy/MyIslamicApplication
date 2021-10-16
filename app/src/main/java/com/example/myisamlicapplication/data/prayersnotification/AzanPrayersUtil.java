package com.example.myisamlicapplication.data.prayersnotification;

import android.content.Context;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class AzanPrayersUtil {

    public static void registerPrayers(Context context) {

        WorkManager.getInstance(context.getApplicationContext()).cancelAllWork();
        PeriodicWorkRequest registerRequest = new PeriodicWorkRequest
                .Builder(RegisterPrayerTimesWorker.class, 30, TimeUnit.DAYS)
                .addTag("REGISTER_PRAYERS")
                .build();
        WorkManager.getInstance(context.getApplicationContext())
                .enqueueUniquePeriodicWork("REGISTER_PRAYERS",
                        ExistingPeriodicWorkPolicy.REPLACE, registerRequest);
    }
}
