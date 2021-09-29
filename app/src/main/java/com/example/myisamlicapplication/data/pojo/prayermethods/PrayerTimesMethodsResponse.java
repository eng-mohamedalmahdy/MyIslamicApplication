package com.example.myisamlicapplication.data.pojo.prayermethods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrayerTimesMethodsResponse {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    private Data data;

    public PrayerTimesMethodsResponse() {
    }

    public PrayerTimesMethodsResponse(Integer code, String status, Data data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
