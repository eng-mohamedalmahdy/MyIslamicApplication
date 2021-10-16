package com.example.myisamlicapplication.ui.quran.quranindex;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myisamlicapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class QuranIndexFragment extends Fragment {

    TabLayout indexTabs;
    ViewPager2 pager;
    QuranIndexPagerAdapter adapter;
    QuranIndexViewModel viewModel;
    EditText search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quran_index, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        indexTabs = view.findViewById(R.id.quran_index_tabs);
        search = view.findViewById(R.id.search_quran_edit_text);
        pager = view.findViewById(R.id.quran_index_pager);
        adapter = new QuranIndexPagerAdapter(this);
        pager.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(QuranIndexViewModel.class);
        new TabLayoutMediator(indexTabs, pager,
                (tab, position) -> tab.setText(viewModel.getTabAt(position))
        ).attach();

        search.setOnClickListener(v ->
                NavHostFragment
                        .findNavController(this)
                        .navigate(QuranIndexFragmentDirections
                                .actionQuranIndexFragmentToQuranSearchFragment()));
    }
}