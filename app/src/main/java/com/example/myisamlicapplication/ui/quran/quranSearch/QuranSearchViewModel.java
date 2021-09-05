package com.example.myisamlicapplication.ui.quran.quranSearch;

import android.content.Context;

import com.example.myisamlicapplication.data.database.QuranDao;
import com.example.myisamlicapplication.data.database.QuranDatabase;
import com.example.myisamlicapplication.data.pojo.Aya;

import java.util.ArrayList;

public class QuranSearchViewModel {

    public ArrayList<Aya> getSearchResult(Context context, String keyword) {
       QuranDao dao = QuranDatabase.getInstance(context).quranDao();
       return (ArrayList<Aya>) dao.getAyaBySubText(keyword);

    }
}
