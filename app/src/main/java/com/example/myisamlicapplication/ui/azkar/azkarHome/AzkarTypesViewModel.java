package com.example.myisamlicapplication.ui.azkar.azkarHome;

import android.content.Context;

import com.example.myisamlicapplication.data.azkarProvider.AzkarProvider;
import com.example.myisamlicapplication.data.pojo.ZekrType;

import java.util.ArrayList;
import java.util.HashSet;

public class AzkarTypesViewModel {

    HashSet<ZekrType> getAzkarTypes(Context context){
       return AzkarProvider.getAzkarTypes(context);
    }

}
