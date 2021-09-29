package com.example.myisamlicapplication.data.pojo.prayermethods;

public class PrayerTimingMethod{
    public int id;
    public String name;
    public Params params;
    public Location location;

    public PrayerTimingMethod(int id, String name, Params params, Location location) {
        this.id = id;
        this.name = name;
        this.params = params;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "PrayerTimingMethod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", params=" + params +
                ", location=" + location +
                '}';
    }

}
