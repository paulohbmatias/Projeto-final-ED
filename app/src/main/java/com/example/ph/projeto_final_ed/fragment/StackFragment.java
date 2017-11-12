package com.example.ph.projeto_final_ed.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ph.projeto_final_ed.R;
import com.example.ph.projeto_final_ed.helper.PilhaEnc;
import com.example.ph.projeto_final_ed.helper.StackAdapter;
import com.example.ph.projeto_final_ed.model.StackModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class StackFragment extends Fragment implements View.OnCreateContextMenuListener{
    private PilhaEnc stack;
    private ListView listView;
    private TextView textSizeStack, textTopStack;


    public StackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stack, container, false);
        listView = view.findViewById(R.id.list);
        FloatingActionButton floatingActionButtonAdd = view.findViewById(R.id.floatingActionButtonAdd);
        FloatingActionButton floatingActionButtonRemove = view.findViewById(R.id.floatingActionButtonRemove);
        textSizeStack = view.findViewById(R.id.text_stack_size);
        textTopStack = view.findViewById(R.id.text_stack_top);

        stack = new PilhaEnc();



        try {
            configuraPilha();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createListDialog(inflater, container);
            }
        });
        floatingActionButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stack.pop();
                try {
                    configuraPilha();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        });


        return view;
    }
    private void createListDialog(LayoutInflater inflater, ViewGroup container){
        View viewDialog = inflater.inflate(R.layout.stack_add, container, false);
        final EditText newContent = viewDialog.findViewById(R.id.et_new_content_id);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setView(viewDialog);
        dialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int content = Integer.parseInt(newContent.getText().toString());
                stack.push(content);
                try {
                    configuraPilha();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.create();
        dialog.show();
    }

    private void configuraPilha() throws CloneNotSupportedException {
        PilhaEnc aux = stack.getClone();

        StackModel[] stackModel = new StackModel[stack.tamanho()];
        for(int i =  0; i<stack.tamanho(); i++){
            stackModel[i] = new StackModel(aux.top());
            aux.pop();
        }
        if(stack.vazia()) {
            textSizeStack.setText("Empty stack");
            textTopStack.setVisibility(View.INVISIBLE);
        }else {
            textSizeStack.setText("Stack Size: " + stack.tamanho());
            textTopStack.setVisibility(View.VISIBLE);
            textTopStack.setText("Top stack: "+stack.top());
        }
        StackAdapter stackAdapter = new StackAdapter(getActivity(), stackModel);
        listView.setAdapter(stackAdapter);
    }




}
