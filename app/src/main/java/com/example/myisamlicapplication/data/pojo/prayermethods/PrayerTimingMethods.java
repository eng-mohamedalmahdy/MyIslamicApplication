package com.example.myisamlicapplication.data.pojo.prayermethods;

import java.util.ArrayList;
import java.util.Comparator;

public class PrayerTimingMethods {

    private ArrayList<PrayerTimingMethod> methods;

    public PrayerTimingMethods(Data data) {
        methods = new ArrayList<>();
        methods.add(data.geteGYPT().toMethod());
        methods.add(data.getfRANCE().toMethod());
        methods.add(data.getgULF().toMethod());
        methods.add(data.getiSNA().toMethod());
        methods.add(data.getjAFARI().toMethod());
        methods.add(data.getkARACHI().toMethod());
        methods.add(data.getkUWAIT().toMethod());
        methods.add(data.getmAKKAH().toMethod());
        methods.add(data.getmOONSIGHTING().toMethod());
        methods.add(data.getmWL().toMethod());
        methods.add(data.getqATAR().toMethod());
        methods.add(data.getrUSSIA().toMethod());
        methods.add(data.getsINGAPORE().toMethod());
        methods.add(data.gettEHRAN().toMethod());
        methods.add(data.gettURKEY().toMethod());
        methods.sort(Comparator.comparingInt(method -> method.id));
    }

    public ArrayList<PrayerTimingMethod> getMethods() {
        return methods;
    }

    @Override
    public String toString() {
        return "PrayerTimingMethods{" +
                "methods=" + methods +
                '}';
    }
}
