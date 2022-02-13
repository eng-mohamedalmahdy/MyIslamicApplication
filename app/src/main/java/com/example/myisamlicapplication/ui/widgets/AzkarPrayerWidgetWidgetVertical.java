package com.example.myisamlicapplication.ui.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.myisamlicapplication.R;
import com.example.myisamlicapplication.data.networking.PrayersRetrofit;
import com.example.myisamlicapplication.data.pojo.prayertimes.Datum;
import com.example.myisamlicapplication.data.pojo.prayertimes.PrayerTimesResponse;
import com.example.myisamlicapplication.data.pojo.prayertimes.Timings;
import com.example.myisamlicapplication.data.prayersnotification.PrayersPreferences;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of App Widget functionality.
 */
public class AzkarPrayerWidgetWidgetVertical extends AppWidgetProvider {

    private static final String TAG = "AzkarPrayerWidgetWidget";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Calendar calendar = Calendar.getInstance();
        PrayersPreferences preferences = new PrayersPreferences(context);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String city = preferences.getCity();
        String country = preferences.getCountry();
        int method = preferences.getMethod();

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.azkar_prayer_widget_widget_vertical);
        Call<PrayerTimesResponse> prayerTimesCall = PrayersRetrofit.getAPI().getPrayerTimes(city, country, method, month, year);

        prayerTimesCall.enqueue(new Callback<PrayerTimesResponse>() {
            @Override
            public void onResponse(Call<PrayerTimesResponse> call, Response<PrayerTimesResponse> response) {
                List<Datum> data = Objects.requireNonNull(response.body()).getData();
                if (data == null) return;
                Timings timings = data.get(day - 1).getTimings();
                views.setTextViewText(R.id.fajr, timings.getFajr().substring(0, 5));
                views.setTextViewText(R.id.dhuhr, timings.getDhuhr().substring(0, 5));
                views.setTextViewText(R.id.asr, timings.getAsr().substring(0, 5));
                views.setTextViewText(R.id.maghrib, timings.getMaghrib().substring(0, 5));
                views.setTextViewText(R.id.isha, timings.getIsha().substring(0, 5));
                // Instruct the widget manager to update the widget
                appWidgetManager.updateAppWidget(appWidgetId, views);
            }

            @Override
            public void onFailure(Call<PrayerTimesResponse> call, Throwable t) {
            }
        });

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}