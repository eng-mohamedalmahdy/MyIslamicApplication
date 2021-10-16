package com.example.myisamlicapplication.ui.prayertimes;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myisamlicapplication.data.citiesprovider.CitiesProvider;
import com.example.myisamlicapplication.data.networking.PrayersRetrofit;
import com.example.myisamlicapplication.data.pojo.prayermethods.PrayerTimesMethodsResponse;
import com.example.myisamlicapplication.data.pojo.prayermethods.PrayerTimingMethods;
import com.example.myisamlicapplication.data.pojo.prayertimes.City;
import com.example.myisamlicapplication.data.pojo.prayertimes.Datum;
import com.example.myisamlicapplication.data.pojo.prayertimes.PrayerTimesResponse;
import com.example.myisamlicapplication.data.pojo.prayertimes.PrayerTiming;
import com.example.myisamlicapplication.data.pojo.prayertimes.Timings;
import com.example.myisamlicapplication.data.prayersnotification.AzanPrayersUtil;
import com.example.myisamlicapplication.data.prayersnotification.PrayersPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrayerTimesViewModel extends AndroidViewModel {


    private static final String TAG = "PrayerViewModel";

    MutableLiveData<City> currentCity;
    MutableLiveData<ArrayList<PrayerTiming>> prayerTimings;
    MutableLiveData<Integer> currentPrayerCalculatingMethod;
    MutableLiveData<PrayerTimingMethods> prayerTimingMethods;
    PrayersPreferences preferences;


    public PrayerTimesViewModel(@NonNull Application application) {
        super(application);
        preferences = new PrayersPreferences(getApplication());
        prayerTimings = new MutableLiveData<>();
        String country = preferences.getCountry();
        currentCity = new MutableLiveData<>(new City(country, preferences.getCity()));
        currentPrayerCalculatingMethod = new MutableLiveData<>(preferences.getMethod());
        prayerTimingMethods = new MutableLiveData<>();
        setPrayerTimingMethods();
    }


    private Call<PrayerTimesMethodsResponse> getPrayerTimingsResponse() {
        return PrayersRetrofit.getAPI().getPrayerTimesMethods();
    }

    private Call<PrayerTimesResponse> getPrayers(String country,
                                                 String city,
                                                 int method,
                                                 int month,
                                                 int year) {
        return PrayersRetrofit.getAPI().getPrayerTimes(city, country, method, month, year);
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

    public MutableLiveData<ArrayList<PrayerTiming>> getPrayerTimings() {
        return prayerTimings;
    }


    public MutableLiveData<City> getCurrentCity() {
        return currentCity;
    }

    public List<City> getCities() {
        return CitiesProvider.getCities(getApplication());
    }

    public MutableLiveData<Integer> getCurrentPrayerCalculatingMethod() {
        return currentPrayerCalculatingMethod;
    }


    public MutableLiveData<PrayerTimingMethods> getPrayerTimingMethods() {
        return prayerTimingMethods;
    }

    public void setPrayerTimings(City city, int method, int day, int month, int year) {
        getPrayers(city.getCountry(),
                city.getName(),
                method,
                month + 1,
                year).enqueue(new Callback<PrayerTimesResponse>() {
            @Override
            public void onResponse(Call<PrayerTimesResponse> call, Response<PrayerTimesResponse> response) {
                List<Datum> data = Objects.requireNonNull(response.body()).getData();
                if (data == null) return;
                Timings timings = data.get(day - 1).getTimings();
                ArrayList<PrayerTiming> prayers = convertFromTimings(timings);
                prayerTimings.setValue(prayers);
                preferences.setCity(city.getName());
                preferences.setCountry(city.getCountry());
                preferences.setMethod(method);
                Log.d(TAG, "onResponse: " + preferences.getMethod());
                AzanPrayersUtil.registerPrayers(getApplication());
            }

            @Override
            public void onFailure(Call<PrayerTimesResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }


    private void setPrayerTimingMethods() {
        getPrayerTimingsResponse().enqueue(new Callback<PrayerTimesMethodsResponse>() {
            @Override
            public void onResponse(Call<PrayerTimesMethodsResponse> call, Response<PrayerTimesMethodsResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().getData());
                prayerTimingMethods.setValue(new PrayerTimingMethods(response.body().getData()));
            }

            @Override
            public void onFailure(Call<PrayerTimesMethodsResponse> call, Throwable t) {
                Log.d(TAG, "onError: " + t.getLocalizedMessage());

            }
        });
    }

    public void setCurrentCity(City city) {
        currentCity.setValue(city);
    }

}
