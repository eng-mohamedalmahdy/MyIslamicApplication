package com.example.myisamlicapplication.ui.quran.quranPage;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.myisamlicapplication.data.PagesManger;

public class QuranViewModel extends AndroidViewModel {


    public QuranViewModel(@NonNull Application application) {
        super(application);
    }

    public Drawable getQuranImageByPageNumber(int pageNumber) {

        return PagesManger.getQuranImageByPageNumber(getApplication(), pageNumber);
    }

}
