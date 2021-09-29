package com.example.myisamlicapplication.data.networking;


import com.example.myisamlicapplication.data.pojo.prayermethods.PrayerTimesMethodsResponse;
import com.example.myisamlicapplication.data.pojo.prayertimes.PrayerTimesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PrayerAPI {

    @GET("calendarByCity")
    Call<PrayerTimesResponse> getPrayerTimes(@Query("city") String city,
                                             @Query("country") String country,
                                             @Query("method") int method,
                                             @Query("month") int month,
                                             @Query("year") int year);

    @GET("methods")
    Call<PrayerTimesMethodsResponse> getPrayerTimesMethods();

}
