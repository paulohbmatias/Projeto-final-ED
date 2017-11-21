package com.example.ph.projeto_final_ed.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ph.projeto_final_ed.R;
import com.example.ph.projeto_final_ed.helper.PilhaEnc;
import com.example.ph.projeto_final_ed.adapter.StackAdapter;
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
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);



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
                int pop = stack.pop();
                if(pop != -1){
                    Toast.makeText(getActivity(), pop+" removido do topo da pilha!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Pilha já está vazia!", Toast.LENGTH_SHORT).show();
                }
                try {
                    configuraPilha();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        });


        return view;
    }
    private void createListDialog(final LayoutInflater inflater, final ViewGroup container){
        View viewDialog = inflater.inflate(R.layout.stack_add, container, false);
        final EditText newContent = viewDialog.findViewById(R.id.et_new_content_id);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setView(viewDialog);
        dialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                try {
                    int content = Integer.parseInt(newContent.getText().toString());
                    if(!newContent.getText().toString().equals("")){

                        if(content>=0){
                            if(stack.push(content)){
                                Toast.makeText(getActivity(), content+" inserido no topo da pilha!", Toast.LENGTH_SHORT).show();
                                try {
                                    configuraPilha();
                                } catch (CloneNotSupportedException e) {
                                    e.printStackTrace();
                                }
                            }else{
                                Toast.makeText(getActivity(),"Falha ao inserir elemento na pilha!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getActivity(),"Não permitido número negativo!", Toast.LENGTH_SHORT).show();
                            createListDialog(inflater, container);
                        }

                    }else {
                        Toast.makeText(getActivity(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                        createListDialog(inflater, container);
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(getActivity(), "Por favor, insira um valor válido!", Toast.LENGTH_SHORT).show();
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
            if(i==0)
                stackModel[i].setTop(true);
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
