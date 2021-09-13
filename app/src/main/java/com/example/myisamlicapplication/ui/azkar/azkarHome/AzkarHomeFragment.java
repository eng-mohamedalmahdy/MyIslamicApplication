package com.example.myisamlicapplication.ui.azkar.azkarHome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myisamlicapplication.R;


public class AzkarHomeFragment extends Fragment {

    private RecyclerView azkarTypes;
    private AzkarTypesViewModel viewModel;
    private AzkarTypesAdapter azkarTypesAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        azkarTypesAdapter = new AzkarTypesAdapter(this);
        viewModel = new AzkarTypesViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_azkar_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        azkarTypes = view.findViewById(R.id.azkar_types_list);
        azkarTypes.setAdapter(azkarTypesAdapter);
        azkarTypes.setLayoutManager(new LinearLayoutManager(getContext()));
        azkarTypesAdapter.setAzkarTypes(viewModel.getAzkarTypes(getContext()));

    }
}