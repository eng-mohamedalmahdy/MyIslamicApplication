package com.example.myisamlicapplication.ui.prayertimes.prayercitypicker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myisamlicapplication.R;
import com.example.myisamlicapplication.ui.prayertimes.PrayerTimesViewModel;


public class PrayerCityPickerFragment extends Fragment {


    private PrayerTimesViewModel viewModel;
    private EditText search;
    private RecyclerView citiesList;
    private CitiesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(PrayerTimesViewModel.class);
        adapter = new CitiesAdapter(viewModel.getCities(), city -> {
            viewModel.setCurrentCity(city);
            NavHostFragment.findNavController(this).navigateUp();
        });
        return inflater.inflate(R.layout.fragment_prayer_city_picker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = view.findViewById(R.id.search_cities);
        citiesList = view.findViewById(R.id.cities_list);
        citiesList.setAdapter(adapter);
        citiesList.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}