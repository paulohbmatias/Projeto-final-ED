package com.example.ph.projeto_final_ed.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ph.projeto_final_ed.R;
import com.example.ph.projeto_final_ed.adapter.ListAdapter;
import com.example.ph.projeto_final_ed.helper.ListaSeq;
import com.example.ph.projeto_final_ed.model.ListModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaSequencialFragment extends Fragment implements SearchView.OnQueryTextListener {

    private ListaSeq listaSeq;
    private ListView listView;
    private ListAdapter listAdapter;
    private ListModel[] listModel;
    private FloatingActionButton floatingActionButton;
    private TextView textSize, textTamMax;
    private Spinner spinner;
    private SearchView searchView;
    public ListaSequencialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_sequencial, container, false);

        listView = view.findViewById(R.id.list);
        floatingActionButton = view.findViewById(R.id.floatingActionButtonAdd);
        textSize = view.findViewById(R.id.text_list_size);
        textTamMax = view.findViewById(R.id.text_tam_max);
        spinner = view.findViewById(R.id.spinner);
        searchView = view.findViewById(R.id.searchView);
        //searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);

        configurarSpinner();

        listaSeq = new ListaSeq();
        listaSeq.iniciarObjeto(10);
        listaSeq.insere(1, 1);
        listaSeq.insere(2, 2);
        listaSeq.insere(3, 3);
        listaSeq.insere(4, 4);
        listaSeq.insere(5, 5);
        listView.setOnCreateContextMenuListener(this);

        if(listaSeq.isIniciada())
            configuraLista(listaSeq, false, 0);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listaSeq.isIniciada())
                    createListDialog(inflater, container);
                else
                    createListInitDialog(inflater, container);

            }
        });

        return view;
    }
    private void createListInitDialog(final LayoutInflater inflater, final ViewGroup container){
        View viewDialog = inflater.inflate(R.layout.list_size_add, container, false);
        final EditText newSize = viewDialog.findViewById(R.id.et_new_list_size_id);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setView(viewDialog);
        dialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if(!newSize.getText().toString().equals("")){
                        int size = Integer.parseInt(newSize.getText().toString());
                        if (size < 0){
                            Toast.makeText(getActivity(), "Por favor, digite um tamanho válido", Toast.LENGTH_SHORT).show();
                            createListInitDialog(inflater, container);
                        }
                        else {
                            listaSeq.iniciarObjeto(size);
                            createListDialog(inflater, container);
                        }
                    }else {
                        Toast.makeText(getActivity(), "Por favor preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(getActivity(), "Por favor, digite um valor válido!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.create();
        dialog.show();
    }
    private void createListDialog(final LayoutInflater inflater, final ViewGroup container){
        View viewDialog = inflater.inflate(R.layout.list_add, container, false);
        final EditText newPosition = viewDialog.findViewById(R.id.et_new_position_id);
        final EditText newContent = viewDialog.findViewById(R.id.et_new_content_id);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setView(viewDialog);
        dialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                try{
                    if(newContent.getText().toString().equals("") || newPosition.getText().toString().equals("")){
                        Toast.makeText(getActivity(), "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show();
                        createListDialog(inflater, container);
                    }else{
                        int position = Integer.parseInt(newPosition.getText().toString());
                        int content = Integer.parseInt(newContent.getText().toString());
                        if(listaSeq.insere(position, content) && position>0) {
                            Toast.makeText(getContext(), content+" inserido na posição "+position, Toast.LENGTH_SHORT).show();
                            configuraLista(listaSeq, false, 0);
                        }else
                            Toast.makeText(getContext(), "Posição inválida", Toast.LENGTH_SHORT).show();

                    }
                }catch (NumberFormatException e){
                    Toast.makeText(getActivity(), "Por favor digite uma posição válida!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.create();
        dialog.show();
    }

    private void configuraLista(ListaSeq listSeqPassada, boolean bposition, int valorAlternativo){
        listModel = new ListModel[listSeqPassada.tamanho()];
        for(int i =  0; i<listSeqPassada.tamanho(); i++){
            listModel[i] = new ListModel(i, listSeqPassada.elemento(i+1));
        }
        textTamMax.setText("Maximum list size: "+listSeqPassada.tamanhoMaximo());
        if(listSeqPassada.vazia())
            textSize.setText("Empty list");
        else {
            textSize.setText("List Size: " + listaSeq.tamanho());
        }

        listAdapter = new ListAdapter(getActivity(), listModel, bposition, valorAlternativo);
        listView.setAdapter(listAdapter);
    }

    private void configurarSpinner(){
        String[] items = new String[]{"Position", "Content"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Remover da lista ");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position;
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        if(item.getTitle().equals("Remover da lista ")){
            position = info.position + 1;
            int remove = listaSeq.remove(position);
            if(remove != -1){
                Toast.makeText(getActivity(), remove+" removido da lista!", Toast.LENGTH_SHORT).show();
                configuraLista(listaSeq, false, 0);
            }else{
                Toast.makeText(getActivity(), "Falha ao remover da lista", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        int q = Integer.parseInt(query);
        if(spinner.getSelectedItemId()==0){
            if(listaSeq.elemento(q) == -1){
                Toast.makeText(getContext(), "Consulta falhou", Toast.LENGTH_SHORT).show();
            }else{
                ListaSeq lde1 = new ListaSeq();
                lde1.insere(1, listaSeq.elemento(q));
                configuraLista(lde1, true, q);
                listAdapter.setNotifyOnChange(true);
            }
        }else {
            if(listaSeq.posicao(q) == -1){
                Toast.makeText(getContext(), "Consulta falhou", Toast.LENGTH_SHORT).show();
            }else{
                ListaSeq lde1 = new ListaSeq();
                lde1.insere(1, q);
                configuraLista(lde1, true, listaSeq.posicao(q));
            }
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.equals(""))
            configuraLista(listaSeq, false, 0);
        return false;
    }

}
