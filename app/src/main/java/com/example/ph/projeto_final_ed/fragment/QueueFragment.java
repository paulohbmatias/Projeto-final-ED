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
import com.example.ph.projeto_final_ed.adapter.QueueAdapter;
import com.example.ph.projeto_final_ed.helper.FilaEnc;
import com.example.ph.projeto_final_ed.adapter.StackAdapter;
import com.example.ph.projeto_final_ed.model.QueueModel;
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
                int remove = queue.remove();
                if(remove != -1){
                    try {
                        Toast.makeText(getActivity(), remove+" removido do inicio da fila", Toast.LENGTH_SHORT).show();
                        configuraFila();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }else
                    Toast.makeText(getActivity(), "Fila já está vazia!", Toast.LENGTH_SHORT).show();

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
                            if(queue.insere(content)){
                                Toast.makeText(getActivity(), content+" inserido no final da fila!", Toast.LENGTH_SHORT).show();
                                try {
                                    configuraFila();
                                } catch (CloneNotSupportedException e) {
                                    e.printStackTrace();
                                }
                            }else{
                                Toast.makeText(getActivity(),"Falha ao inserir elemento na fila!", Toast.LENGTH_SHORT).show();
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

    private void configuraFila() throws CloneNotSupportedException {
        FilaEnc aux = queue.getClone();

        QueueModel[] queueModel = new QueueModel[queue.tamanho()];
        for(int i = 0; i< queue.tamanho(); i++){
            queueModel[i] = new QueueModel(aux.primeiro());
            if(i==queue.tamanho()-1)
                queueModel[i].setEnd(true);
            if(i==0)
                queueModel[i].setTop(true);
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
        QueueAdapter queueAdapter = new QueueAdapter(getActivity(), queueModel);
        listView.setAdapter(queueAdapter);
    }

}
