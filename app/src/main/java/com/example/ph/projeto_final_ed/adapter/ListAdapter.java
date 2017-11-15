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
import com.example.ph.projeto_final_ed.model.ListModel;

public class ListAdapter extends ArrayAdapter<ListModel> {
    private Context context;
    private ListModel[] listModel;
    private boolean bposition;
    private int valorAlternativo;

    public ListAdapter(@NonNull Context context, @NonNull ListModel[] objects, boolean bposition, int valorAlternativo) {
        super(context, 0, 0, objects);
        this.context = context;
        this.listModel = objects;
        this.bposition = bposition;
        this.valorAlternativo = valorAlternativo;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if(listModel != null){
            //Inicializa objeto para montagem de Layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Monta a view a partir do xml
            view = inflater.inflate(R.layout.model_list, parent, false);

            //Recuperar elementos para exibição
            TextView tposition = view.findViewById(R.id.text_position);
            TextView tcontent = view.findViewById(R.id.text_content);



            //Adicionar elementos a lista
            if(bposition)
                tposition.setText(""+valorAlternativo);
            else
                tposition.setText(""+(position+1));
            tcontent.setText(""+ listModel[position].getContent());
        }



        return view;
    }
}
