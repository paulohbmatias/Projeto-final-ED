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

import com.example.ph.projeto_final_ed.R;
import com.example.ph.projeto_final_ed.helper.FilaEnc;
import com.example.ph.projeto_final_ed.helper.PilhaEnc;
import com.example.ph.projeto_final_ed.helper.StackAdapter;
import com.example.ph.projeto_final_ed.model.StackModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueueFragment extends Fragment {
    private FilaEnc queue;
    private ListView listView;
    private TextView textSizeQueue, textFrontElement;

    public QueueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_queue, container, false);
        listView = view.findViewById(R.id.list);
        FloatingActionButton floatingActionButtonAdd = view.findViewById(R.id.floatingActionButtonAdd);
        FloatingActionButton floatingActionButtonRemove = view.findViewById(R.id.floatingActionButtonRemove);
        textSizeQueue = view.findViewById(R.id.text_queue_size);
        textFrontElement = view.findViewById(R.id.text_queue_front_element);

        queue = new FilaEnc();



        try {
            configuraFila();
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
                queue.remove();
                try {
                    configuraFila();
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
                queue.insere(content);
                try {
                    configuraFila();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.create();
        dialog.show();
    }

    private void configuraFila() throws CloneNotSupportedException {
        FilaEnc aux = queue.getClone();

        StackModel[] stackModel = new StackModel[queue.tamanho()];
        for(int i = 0; i< queue.tamanho(); i++){
            stackModel[i] = new StackModel(aux.primeiro());
            aux.remove();
        }
        if(queue.vazia()) {
            textSizeQueue.setText("Empty queue");
            textFrontElement.setVisibility(View.INVISIBLE);
        }else {
            textSizeQueue.setText("Queue Size: " + queue.tamanho());
            textFrontElement.setVisibility(View.VISIBLE);
            textFrontElement.setText("Front element: "+ queue.primeiro());
        }
        StackAdapter stackAdapter = new StackAdapter(getActivity(), stackModel);
        listView.setAdapter(stackAdapter);
    }

}
