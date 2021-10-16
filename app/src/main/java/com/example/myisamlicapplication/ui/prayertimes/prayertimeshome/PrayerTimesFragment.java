package com.example.myisamlicapplication.ui.prayertimes.prayertimeshome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myisamlicapplication.R;
import com.example.myisamlicapplication.ui.prayertimes.PrayerTimesViewModel;
import com.example.myisamlicapplication.ui.prayertimes.prayercitypicker.CitiesAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.stream.Collectors;

public class PrayerTimesFragment extends Fragment {
    private static final String TAG = "PrayerTimesFragment";
    private RecyclerView times;
    private DatePicker datePicker;
    private TextView currentCity;
    private Spinner prayerTimingMethodsSpinner;
    private PrayerTimesListAdapter adapter;
    private PrayerTimesViewModel viewModel;
    private boolean firstTimeSpinner = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(PrayerTimesViewModel.class);
        return inflater.inflate(R.layout.fragment_prayer_times, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setupAdapters();
        initDatePicker();
        initClickListeners();
        setupObservers();

    }

    private void initViews(View view) {
        times = view.findViewById(R.id.prayers_list);
        datePicker = view.findViewById(R.id.date);
        currentCity = view.findViewById(R.id.cities);
        prayerTimingMethodsSpinner = view.findViewById(R.id.method);
        prayerTimingMethodsSpinner.setSelection(viewModel.getCurrentPrayerCalculatingMethod().getValue());

    }

    private void setupAdapters() {
        adapter = new PrayerTimesListAdapter();
        times.setAdapter(adapter);
    }

    private void initDatePicker() {

        Calendar calendar = Calendar.getInstance();
        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), (view1, year, monthOfYear, dayOfMonth) ->
                {
                    if (viewModel.getCurrentCity().getValue() != null
                            && viewModel.getCurrentPrayerCalculatingMethod().getValue() != null) {
                        viewModel.
                                setPrayerTimings(viewModel.getCurrentCity().getValue(),
                                        viewModel.getCurrentPrayerCalculatingMethod().getValue(),
                                        dayOfMonth,
                                        monthOfYear+1,
                                        year);
                    }
                });

    }

    private void initClickListeners() {
        currentCity.setOnClickListener(v -> NavHostFragment
                .findNavController(this)
                .navigate(PrayerTimesFragmentDirections
                        .actionPrayerTimesFragmentToPrayerCityPickerFragment()));

        prayerTimingMethodsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!firstTimeSpinner) {
                    viewModel.getCurrentPrayerCalculatingMethod().setValue(position);
                    Log.d(TAG, "onItemSelected: " + position);
                }
                firstTimeSpinner = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupObservers() {
        viewModel.getPrayerTimings().observe(getViewLifecycleOwner(),
                prayerTimings -> adapter.setTimings(prayerTimings));

        viewModel.getCurrentCity().observe(getViewLifecycleOwner(), city -> {
            if (viewModel.getCurrentPrayerCalculatingMethod().getValue() != null)
                viewModel.setPrayerTimings(city,
                        viewModel.getCurrentPrayerCalculatingMethod().getValue(),
                        datePicker.getDayOfMonth(),
                        datePicker.getMonth(),
                        datePicker.getYear());

            currentCity.setText((city.getName() + ", " + city.getCountry()));
        });

        viewModel.getCurrentPrayerCalculatingMethod().observe(getViewLifecycleOwner(), prayerMethod -> {
            if (viewModel.getCurrentCity().getValue() != null)
                viewModel.setPrayerTimings(
                        viewModel.getCurrentCity().getValue(),
                        prayerMethod,
                        datePicker.getDayOfMonth(),
                        datePicker.getMonth(),
                        datePicker.getYear());
        });
        viewModel.getPrayerTimingMethods().observe(getViewLifecycleOwner(),
                prayerTimingsMethods ->
                        prayerTimingMethodsSpinner.
                                setAdapter(new PrayerMethodsAdapter(requireContext(),
                                        prayerTimingsMethods
                                                .getMethods()
                                                .stream().
                                                sorted(Comparator.comparing(prayerTimingMethod -> prayerTimingMethod.id))
                                                .collect(Collectors.toCollection(ArrayList::new)))));
    }


}