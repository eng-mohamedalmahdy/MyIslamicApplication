package com.example.myisamlicapplication.data.citiesprovider;

import android.content.Context;
import android.util.Log;

import com.example.myisamlicapplication.data.pojo.prayertimes.City;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CitiesProvider {
    private static final String TAG = "CitiesProvider";

    public static List<City> getCities(Context context) {
        try {
            InputStream jsonFile = context.getAssets().open("cities/cities.json");
            int size = jsonFile.available();
            byte[] bytes = new byte[size];
            jsonFile.read(bytes);
            jsonFile.close();
            String citiesString = new String(bytes, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            ArrayList<City> cities = gson.fromJson(citiesString, new TypeToken<List<City>>() {
            }.getType());
            return cities;
        } catch (IOException e) {
            Log.d(TAG, "getCities: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }
}
