package com.example.myisamlicapplication.ui.azkar.azkarList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myisamlicapplication.R;

import java.util.ArrayList;


public class AzkarListFragment extends Fragment {

    private AzkarListFragmentArgs args;
    private AzkarListViewModel viewModel;
    private RecyclerView azkarListRecyclerView;
    private AzkarListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = AzkarListFragmentArgs.fromBundle(requireArguments());
        viewModel = new ViewModelProvider(this).get(AzkarListViewModel.class);
        adapter = new AzkarListAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_azkar_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        azkarListRecyclerView = view.findViewById(R.id.azkar_list);
        azkarListRecyclerView.setAdapter(adapter);
        azkarListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setAzkar(viewModel.getAzkar(args.getAzkarType()));
    }
}