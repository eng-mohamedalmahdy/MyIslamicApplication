package com.example.myisamlicapplication.data.prayernotifications;

import android.content.Context;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class AzanPrayerUtils {

    public static void registerPrayers(Context context) {

        //First we clear all the previous work
        WorkManager.getInstance(context).cancelAllWork();

        PeriodicWorkRequest registerPrayerTimesWorkRequest = new
                PeriodicWorkRequest.Builder(RegisterPrayerTimesWorker.class, 30, TimeUnit.DAYS)
                .addTag("PRAYERS_FETCH")
                .build();
        //Enqueue unique Periodic work
        WorkManager
                .getInstance(context)
                .enqueueUniquePeriodicWork("PRAYERS_FETCH",
                        ExistingPeriodicWorkPolicy.REPLACE,
                        registerPrayerTimesWorkRequest);
    }
}
