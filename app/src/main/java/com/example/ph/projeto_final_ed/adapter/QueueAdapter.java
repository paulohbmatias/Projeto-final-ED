package com.example.ph.projeto_final_ed.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ph.projeto_final_ed.R;
import com.example.ph.projeto_final_ed.model.QueueModel;

public class QueueAdapter extends ArrayAdapter<QueueModel> {
    private Context context;
    private QueueModel[] queueckModel;

    public QueueAdapter(@NonNull Context context, @NonNull QueueModel[] objects) {
        super(context, 0, 0, objects);
        this.context = context;
        this.queueckModel = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if(queueckModel != null){
            //Inicializa objeto para montagem de Layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Monta a view a partir do xml
            assert inflater != null;
            view = inflater.inflate(R.layout.model_queue, parent, false);
            if(queueckModel[position].isEnd())
                view = inflater.inflate(R.layout.model_end_queue, parent, false);
            if(queueckModel[position].isTop())
                view = inflater.inflate(R.layout.model_top_queue, parent, false);


            //Recuperar elementos para exibição
            TextView stackItem = view.findViewById(R.id.item_stack);



            //Adicionar elementos a list
            stackItem.setText(""+ queueckModel[position].getContent());
        }



        return view;
    }
}
