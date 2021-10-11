package com.example.myisamlicapplication.data.prayernotifications;

import android.content.Context;
import android.icu.util.Calendar;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.myisamlicapplication.data.networking.PrayersRetrofit;
import com.example.myisamlicapplication.data.pojo.prayertimes.Datum;
import com.example.myisamlicapplication.data.pojo.prayertimes.PrayerTimesResponse;
import com.example.myisamlicapplication.data.pojo.prayertimes.PrayerTiming;
import com.example.myisamlicapplication.data.pojo.prayertimes.Timings;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Response;

public class RegisterPrayerTimesWorker extends Worker {
    private static final String TAG = "RegisterPrayerTimes";

    public RegisterPrayerTimesWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            PrayersPreferences preferences = new PrayersPreferences(getApplicationContext());
            Response<PrayerTimesResponse> prayerTimesResponse =
                    PrayersRetrofit.getAPI().getPrayerTimes(preferences.getCity(),
                            preferences.getCountry(),
                            preferences.getMethod(), month, year
                    ).execute();

            List<Datum> data = prayerTimesResponse.body().getData();
            Log.d(TAG, "doWork: " + data);

            data.forEach(datum -> {
                Timings timings = datum.getTimings();
                ArrayList<PrayerTiming> prayers = convertFromTimings(timings);
                prayers.forEach(prayerTiming -> {
                    Data inputData = new Data.Builder()
                            .putString(AzanNotificationConstants.TITLE_KEY, prayerTiming.getPrayerName())
                            .putString(AzanNotificationConstants.CONTENT_KEY, "حي على الصلاة")
                            .build();

                    String prayerTag = prayerTiming.getPrayerName() + " " + year + "/" + month + "/" + day;
                    OneTimeWorkRequest prayerWorkRequest = new OneTimeWorkRequest
                            .Builder(AzanNotificationWorker.class)
                            .addTag(prayerTag)
                            .setInitialDelay(calculatePrayerDelay(year, month, day, prayerTiming), TimeUnit.MILLISECONDS)
                            .setInputData(inputData)
                            .build();

                    WorkManager.getInstance(getApplicationContext())
                            .enqueueUniqueWork(prayerTag, ExistingWorkPolicy.REPLACE, prayerWorkRequest);

                });
            });

        } catch (IOException e) {
            e.printStackTrace();
            return Result.retry();
        }
        return Result.success();
    }

    private long calculatePrayerDelay(int year, int month, int day, PrayerTiming prayerTiming) {
        String prayerTime = prayerTiming.getPrayerTime().split(" ")[0];
        try {
            DecimalFormat formatter = new DecimalFormat("00");
            String prayerDate = "" + formatter.format(year)
                    + "/"
                    + formatter.format(month)
                    + "/"
                    + formatter.format(day)
                    + " "
                    + prayerTime;
            Date simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(prayerDate);
            Log.d(TAG, "calculatePrayerDelay: " + simpleDateFormat.toString());
            Log.d(TAG, "calculatePrayerDelay: " + (System.currentTimeMillis() - simpleDateFormat.getTime()));
            return System.currentTimeMillis() - simpleDateFormat.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    ArrayList<PrayerTiming> convertFromTimings(Timings timings) {
        ArrayList<PrayerTiming> res = new ArrayList<>();
        res.add(new PrayerTiming("Fajr", timings.getFajr()));
        res.add(new PrayerTiming("Dhuhr", timings.getDhuhr()));
        res.add(new PrayerTiming("Asr", timings.getAsr()));
        res.add(new PrayerTiming("Maghrib", timings.getMaghrib()));
        res.add(new PrayerTiming("Isha", timings.getIsha()));
        return res;
    }
}
