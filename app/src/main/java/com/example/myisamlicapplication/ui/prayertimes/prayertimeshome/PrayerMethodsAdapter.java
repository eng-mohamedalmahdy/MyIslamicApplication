package com.example.myisamlicapplication.ui.prayertimes.prayertimeshome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myisamlicapplication.R;
import com.example.myisamlicapplication.data.pojo.prayermethods.PrayerTimingMethod;
import com.example.myisamlicapplication.data.pojo.prayertimes.City;

import java.util.ArrayList;

public class PrayerMethodsAdapter extends ArrayAdapter<PrayerTimingMethod> {
    public PrayerMethodsAdapter(@NonNull Context context, ArrayList<PrayerTimingMethod> methods) {
        super(context, 0, methods);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }


    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext()).inflate(R.layout.list_item_prayer_method, parent, false);
            PrayerTimingMethod method = getItem(position);
            TextView methodId = convertView.findViewById(R.id.prayer_method_id);
            TextView methodName = convertView.findViewById(R.id.prayer_method_name);
            methodId.setText(Integer.toString(method.id));
            methodName.setText(method.name);
        }
        return convertView;
    }


}
