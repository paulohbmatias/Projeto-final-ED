package com.example.ph.projeto_final_ed.helper;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ph.projeto_final_ed.R;
import com.example.ph.projeto_final_ed.model.StackModel;
import com.example.ph.projeto_final_ed.model.TreeModel;

public class TreeAdapter extends ArrayAdapter<TreeModel> {
    private Context context;
    private TreeModel[] stackModel;

    public TreeAdapter(@NonNull Context context, @NonNull TreeModel[] objects) {
        super(context, 0, 0,objects);
        this.context = context;
        this.stackModel = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if(stackModel != null){
            //Inicializa objeto para montagem de Layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Monta a view a partir do xml
            view = inflater.inflate(R.layout.model_tree, parent, false);

            //Recuperar elementos para exibição
            TextView stackItem = view.findViewById(R.id.item_stack);



            //Adicionar elementos a list
            stackItem.setText(""+stackModel[position].getTextView());
        }



        return view;
    }
}
