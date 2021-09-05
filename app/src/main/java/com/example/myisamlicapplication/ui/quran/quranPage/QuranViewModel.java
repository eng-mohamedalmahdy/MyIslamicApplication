package com.example.myisamlicapplication.ui.quran.quranPage;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.myisamlicapplication.data.PagesManger;

public class QuranViewModel {


    public Drawable getQuranImageByPageNumber(Context context, int pageNumber) {

        return PagesManger.getQuranImageByPageNumber(context,pageNumber);
    }

}
