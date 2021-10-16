package com.example.myisamlicapplication.ui.azkar.azkarHome;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.myisamlicapplication.data.azkarProvider.AzkarProvider;
import com.example.myisamlicapplication.data.pojo.azkar.ZekrType;

import java.util.HashSet;

public class AzkarTypesViewModel extends ViewModel {

    HashSet<ZekrType> getAzkarTypes(Context context){
       return AzkarProvider.getAzkarTypes(context);
    }

}
