package com.example.ph.projeto_final_ed.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ph.projeto_final_ed.R;
import com.example.ph.projeto_final_ed.adapter.TabListAdapter;
import com.example.ph.projeto_final_ed.adapter.TabTreeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListOptionFragment extends Fragment {


    public ListOptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_option, container, false);
        ViewPager viewPager = view.findViewById(R.id.vp_pagina);
        TabLayout tabLayout = view.findViewById(R.id.tb_layout);
        TabListAdapter tabListAdapter = new TabListAdapter(getFragmentManager());
        viewPager.setAdapter(tabListAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
