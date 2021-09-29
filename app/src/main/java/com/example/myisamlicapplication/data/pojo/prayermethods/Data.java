package com.example.myisamlicapplication.data.pojo.prayermethods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data{

    @SerializedName("MWL")
    @Expose
    public MWL mWL;

    @SerializedName("ISNA")
    @Expose
    public ISNA iSNA;

    @SerializedName("EGYPT")
    @Expose
    public EGYPT eGYPT;

    @SerializedName("MAKKAH")
    @Expose
    public MAKKAH mAKKAH;

    @SerializedName("KARACHI")
    @Expose
    public KARACHI kARACHI;

    @SerializedName("TEHRAN")
    @Expose
    public TEHRAN tEHRAN;

    @SerializedName("JAFARI")
    @Expose
    public JAFARI jAFARI;

    @SerializedName("GULF")
    @Expose
    public GULF gULF;

    @SerializedName("KUWAIT")
    @Expose
    public KUWAIT kUWAIT;

    @SerializedName("QATAR")
    @Expose
    public QATAR qATAR;

    @SerializedName("SINGAPORE")
    @Expose
    public SINGAPORE sINGAPORE;

    @SerializedName("FRANCE")
    @Expose
    public FRANCE fRANCE;

    @SerializedName("TURKEY")
    @Expose
    public TURKEY tURKEY;

    @SerializedName("RUSSIA")
    @Expose
    public RUSSIA rUSSIA;

    @SerializedName("MOONSIGHTING")
    @Expose
    public MOONSIGHTING mOONSIGHTING;

    public MWL getmWL() {
        return mWL;
    }

    public ISNA getiSNA() {
        return iSNA;
    }

    public EGYPT geteGYPT() {
        return eGYPT;
    }

    public MAKKAH getmAKKAH() {
        return mAKKAH;
    }

    public KARACHI getkARACHI() {
        return kARACHI;
    }

    public TEHRAN gettEHRAN() {
        return tEHRAN;
    }

    public JAFARI getjAFARI() {
        return jAFARI;
    }

    public GULF getgULF() {
        return gULF;
    }

    public KUWAIT getkUWAIT() {
        return kUWAIT;
    }

    public QATAR getqATAR() {
        return qATAR;
    }

    public SINGAPORE getsINGAPORE() {
        return sINGAPORE;
    }

    public FRANCE getfRANCE() {
        return fRANCE;
    }

    public TURKEY gettURKEY() {
        return tURKEY;
    }

    public RUSSIA getrUSSIA() {
        return rUSSIA;
    }

    public MOONSIGHTING getmOONSIGHTING() {
        return mOONSIGHTING;
    }
}
