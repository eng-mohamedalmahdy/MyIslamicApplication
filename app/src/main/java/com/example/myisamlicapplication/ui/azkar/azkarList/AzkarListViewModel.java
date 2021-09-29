package com.example.myisamlicapplication.ui.azkar.azkarList;

import android.content.Context;

import com.example.myisamlicapplication.data.azkarProvider.AzkarProvider;
import com.example.myisamlicapplication.data.pojo.azkar.Zekr;

import java.util.ArrayList;

public class AzkarListViewModel {

    public ArrayList<Zekr> getAzkar(Context context,String zekerType) {
        return AzkarProvider.getAzkarByType(context,zekerType);
    }
}
