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
import com.example.myisamlicapplication.data.pojo.prayertimes.City;

import java.util.List;

public class CitiesAdapter extends ArrayAdapter<City> {


    public CitiesAdapter(@NonNull Context context, List<City> cities) {
        super(context, 0,cities);
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
                    .from(getContext()).inflate(R.layout.list_item_city, parent, false);
            City city = getItem(position);
            TextView cityText = convertView.findViewById(R.id.city);
            TextView countryCodeText = convertView.findViewById(R.id.country_code);
            cityText.setText(city.getName());
            countryCodeText.setText(city.getCountry());
        }
        return convertView;
    }



}
