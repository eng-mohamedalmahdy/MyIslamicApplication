
package com.example.myisamlicapplication.data.pojo.prayertimes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timings {

    @SerializedName("Fajr")
    @Expose
    private String fajr;
    @SerializedName("Sunrise")
    @Expose
    private String sunrise;
    @SerializedName("Dhuhr")
    @Expose
    private String dhuhr;
    @SerializedName("Asr")
    @Expose
    private String asr;
    @SerializedName("Sunset")
    @Expose
    private String sunset;
    @SerializedName("Maghrib")
    @Expose
    private String maghrib;
    @SerializedName("Isha")
    @Expose
    private String isha;
    @SerializedName("Imsak")
    @Expose
    private String imsak;
    @SerializedName("Midnight")
    @Expose
    private String midnight;

    public String getFajr() {
        return fajr;
    }

    public void setFajr(String fajr) {
        this.fajr = fajr;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getDhuhr() {
        return dhuhr;
    }

    public void setDhuhr(String dhuhr) {
        this.dhuhr = dhuhr;
    }

    public String getAsr() {
        return asr;
    }

    public void setAsr(String asr) {
        this.asr = asr;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public String getIsha() {
        return isha;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }

    public String getImsak() {
        return imsak;
    }

    public void setImsak(String imsak) {
        this.imsak = imsak;
    }

    public String getMidnight() {
        return midnight;
    }

    public void setMidnight(String midnight) {
        this.midnight = midnight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Timings.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("fajr");
        sb.append('=');
        sb.append(((this.fajr == null)?"<null>":this.fajr));
        sb.append(',');
        sb.append("sunrise");
        sb.append('=');
        sb.append(((this.sunrise == null)?"<null>":this.sunrise));
        sb.append(',');
        sb.append("dhuhr");
        sb.append('=');
        sb.append(((this.dhuhr == null)?"<null>":this.dhuhr));
        sb.append(',');
        sb.append("asr");
        sb.append('=');
        sb.append(((this.asr == null)?"<null>":this.asr));
        sb.append(',');
        sb.append("sunset");
        sb.append('=');
        sb.append(((this.sunset == null)?"<null>":this.sunset));
        sb.append(',');
        sb.append("maghrib");
        sb.append('=');
        sb.append(((this.maghrib == null)?"<null>":this.maghrib));
        sb.append(',');
        sb.append("isha");
        sb.append('=');
        sb.append(((this.isha == null)?"<null>":this.isha));
        sb.append(',');
        sb.append("imsak");
        sb.append('=');
        sb.append(((this.imsak == null)?"<null>":this.imsak));
        sb.append(',');
        sb.append("midnight");
        sb.append('=');
        sb.append(((this.midnight == null)?"<null>":this.midnight));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
