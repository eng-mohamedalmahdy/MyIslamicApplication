package com.example.myisamlicapplication.data.pojo.prayertimes;

public class City {

    private String country, name;
    private double lat, lng;

    public City() {
    }

    public City(String country, String name, double lat, double lng) {
        this.country = country;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "City{" +
                "countryCode='" + country + '\'' +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
