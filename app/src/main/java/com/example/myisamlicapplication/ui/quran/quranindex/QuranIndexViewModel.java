package com.example.myisamlicapplication.ui.quran.quranindex;

import com.example.myisamlicapplication.data.utils.IndexTabsUtils;

public class QuranIndexViewModel {

    private String[] tabsList;

    public QuranIndexViewModel() {
        tabsList = IndexTabsUtils.QURAN_INDEX_TABS;
    }

    public String getTabAt(int position) {
        return tabsList[position];
    }


}
