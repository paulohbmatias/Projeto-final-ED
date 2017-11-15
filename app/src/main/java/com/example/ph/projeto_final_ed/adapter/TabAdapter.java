package com.example.ph.projeto_final_ed.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ph.projeto_final_ed.fragment.TreeABPFragment;
import com.example.ph.projeto_final_ed.fragment.TreeFragmentArvBin;

/**
 * Created by ph on 15/11/17.
 */

public class TabAdapter extends FragmentStatePagerAdapter{
    private String[] tituloAbas = {"Árvore Binária", "Árvore Binária de Pesquisa"};
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0: fragment = new TreeFragmentArvBin(); break;
            case 1: fragment = new TreeABPFragment(); break;
            default: fragment = null; break;
        }


        return fragment;
    }

    @Override
    public int getCount() {

        return tituloAbas.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tituloAbas[position];
    }
}
