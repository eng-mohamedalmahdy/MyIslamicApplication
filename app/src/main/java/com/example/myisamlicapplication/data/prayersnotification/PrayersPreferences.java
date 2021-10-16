package com.example.myisamlicapplication.data.prayersnotification;

import android.content.Context;
import android.content.SharedPreferences;

public class PrayersPreferences {
    private static final String FILE_NAME = "PRAYERS_PREF";
    private static final String CITY_KEY = "CITY_PREF";
    private static final String COUNTRY_KEY = "COUNTRY_PREF";
    private static final String METHOD_KEY = "METHOD_PREF";
    private final SharedPreferences preferences;

    public PrayersPreferences(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public String getCity() {
        return preferences.getString(CITY_KEY, "cairo");
    }

    public void setCity(String city) {
        preferences.edit().putString(CITY_KEY, city).apply();
    }

    public String getCountry() {
        return preferences.getString(COUNTRY_KEY, "EG");
    }

    public void setCountry(String country) {
        preferences.edit().putString(COUNTRY_KEY, country).apply();
    }

    public int getMethod() {
        return preferences.getInt(METHOD_KEY, 5);
    }

    public void setMethod(int method) {
        preferences.edit().putInt(METHOD_KEY, method).apply();
    }


}
