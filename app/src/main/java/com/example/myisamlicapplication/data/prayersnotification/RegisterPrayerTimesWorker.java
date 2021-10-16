package com.example.myisamlicapplication.data.prayersnotification;

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
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Response;

public class RegisterPrayerTimesWorker extends Worker {

    public RegisterPrayerTimesWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            Calendar calendar = Calendar.getInstance();
            PrayersPreferences preferences = new PrayersPreferences(getApplicationContext());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            String city = preferences.getCity();
            String country = preferences.getCountry();
            int method = preferences.getMethod();

            Response<PrayerTimesResponse> timesResponse = PrayersRetrofit.getAPI().getPrayerTimes(city, country, method, month, year).execute();
            if (timesResponse.isSuccessful()) {
                List<Datum> data = timesResponse.body().getData();
                for (int i = 0; i < data.size(); i++) {
                    Datum datum = data.get(i);
                    Timings timings = datum.getTimings();
                    ArrayList<PrayerTiming> prayers = convertFromTimings(timings);
                    int day = i + 1;
                    prayers.forEach(prayerTiming -> {

                        String prayerTag = "" + year + "/" + month + "/" + day + " " + prayerTiming.getPrayerName();
                        Data input = new Data.Builder()
                                .putString(AzanNotificationConstants.NOTIFICATION_TITLE_KEY, prayerTiming.getPrayerName())
                                .putString(AzanNotificationConstants.NOTIFICATION_CONTENT_KEY, "حي على الصلاة")
                                .build();

                        OneTimeWorkRequest registerPrayerRequest = new OneTimeWorkRequest
                                .Builder(AzanNotificationWorker.class)
                                .addTag(prayerTag)
                                .setInitialDelay(calculatePrayerDelay(year, month, day, prayerTiming), TimeUnit.MILLISECONDS)
                                .setInputData(input)
                                .build();

                        WorkManager.getInstance(getApplicationContext())
                                .enqueueUniqueWork(prayerTag, ExistingWorkPolicy.REPLACE, registerPrayerRequest);
                    });
                }
                return Result.success();
            } else {
                return Result.failure();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.retry();
        }
    }

    private long calculatePrayerDelay(int year, int month, int day, PrayerTiming prayerTiming) {
        String pattern = "yyyy/MM/dd HH:mm";
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String time = prayerTiming.getPrayerTime().split(" ")[0];
        String prayerDate = "" + year + "/" + decimalFormat.format(month) + "/" + decimalFormat.format(day) + " " + time;
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());

        try {
            Date date = format.parse(prayerDate);
            long currentTime = System.currentTimeMillis();
            Log.d("TAG", "calculatePrayerDelay: " + date.toString());
            Log.d("TAG", "calculatePrayerDelay: " + Math.abs(date.getTime() - currentTime));
            return Math.abs(date.getTime() - currentTime);
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
