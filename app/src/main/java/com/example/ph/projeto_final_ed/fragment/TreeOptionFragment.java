package com.example.ph.projeto_final_ed.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.ph.projeto_final_ed.R;
import com.example.ph.projeto_final_ed.adapter.TabAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TreeOptionFragment extends Fragment {


    public TreeOptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tree_option, container, false);
        ViewPager viewPager = view.findViewById(R.id.vp_pagina);
        TabLayout tabLayout = view.findViewById(R.id.tb_layout);
        TabAdapter tabAdapter = new TabAdapter(getFragmentManager());
        viewPager.setAdapter(tabAdapter);

        tabLayout.setupWithViewPager(viewPager);
        return view;
    }


}
