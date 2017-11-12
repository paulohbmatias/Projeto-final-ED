package com.example.ph.projeto_final_ed.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ph.projeto_final_ed.R;
import com.example.ph.projeto_final_ed.helper.PilhaEnc;
import com.example.ph.projeto_final_ed.helper.StackAdapter;
import com.example.ph.projeto_final_ed.helper.TreeAdapter;
import com.example.ph.projeto_final_ed.model.StackModel;
import com.example.ph.projeto_final_ed.model.TreeModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TreeFragment extends Fragment {
    private ListView listView;

    public TreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tree, container, false);
        listView = view.findViewById(R.id.list);
        configTree();


        return view;
    }

    private void configTree(){

    }

    /*
    private void configTree() {
        TreeModel[] treeModel = new TreeModel[2];
        treeModel[0] = new TreeModel();
        treeModel[0].setTextView("Item 1");
        treeModel[1] = new TreeModel();
        treeModel[1].setTextView("Item 2");
        TreeAdapter treeAdapter = new TreeAdapter(getActivity(), treeModel);
        listView.setAdapter(treeAdapter);
    }
    */

}
