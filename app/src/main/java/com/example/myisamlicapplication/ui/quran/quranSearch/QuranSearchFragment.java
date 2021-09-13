package com.example.myisamlicapplication.ui.quran.quranSearch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myisamlicapplication.R;
import com.example.myisamlicapplication.data.database.QuranDao;
import com.example.myisamlicapplication.data.database.QuranDatabase;
import com.example.myisamlicapplication.data.pojo.Aya;

import java.util.ArrayList;


public class QuranSearchFragment extends Fragment {

    private QuranSearchViewModel viewModel;

    private EditText searchEditText;
    private RecyclerView searchResultRecyclerView;

    private QuranSearchAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new QuranSearchAdapter(this);
        viewModel = new QuranSearchViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_quran_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchEditText = view.findViewById(R.id.search_quran_edit_text);
        searchResultRecyclerView = view.findViewById(R.id.quran_search_result);
        searchResultRecyclerView.setAdapter(adapter);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<Aya> ayat = viewModel.getSearchResult(getContext(),s.toString());
                adapter.setAyat(ayat);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}