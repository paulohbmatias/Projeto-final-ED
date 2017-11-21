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
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ph.projeto_final_ed.R;
import com.example.ph.projeto_final_ed.activity.MyHolder;
import com.example.ph.projeto_final_ed.atv.model.TreeNode;
import com.example.ph.projeto_final_ed.atv.view.AndroidTreeView;
import com.example.ph.projeto_final_ed.helper.ArvBin;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TreeFragmentArvBin extends Fragment implements SearchView.OnQueryTextListener{
    private ListView listView;
    private ExpandableListView expandableListView;
    private FloatingActionButton fab;
    private Spinner spinner_position;
    private Spinner spinner_parent;
    private SearchView searchView;
    private static final int PLUSMARGIN = 100;
    private ArvBin arvBin;
    private View view;
    public TreeFragmentArvBin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tree_arvbin, container, false);
        fab = view.findViewById(R.id.floatingActionButtonAdd);
        searchView = view.findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
        //listView = view.findViewById(R.id.list);
       // expandableListView = view.findViewById(R.id.list);
        //configTreeTeste(view);
        arvBin = new ArvBin();
//        arvBin.insereRaiz(2);
//        arvBin.insereEsq(2, 3);
//        arvBin.insereDir(2, 4);
//        arvBin.insereEsq(3, 5);
//        arvBin.insereDir(3, 6);
//        arvBin.insereEsq(4, 7);
//        arvBin.insereDir(4, 8);
//        arvBin.insereEsq(5, 9);
//        arvBin.insereDir(5, 10);
//        arvBin.insereEsq(6, 11);
//        arvBin.insereDir(6, 12);
//        arvBin.insereEsq(12, 14);
//        arvBin.insereDir(12, 13);
//        arvBin.insereEsq(8, 15);
//        arvBin.insereDir(8, 16);

        configuraArvore(arvBin, view);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arvBin.vazia())
                    createTreeRootDialog(inflater, container, arvBin);
                else
                    createTreeDialog(inflater, container, arvBin);
            }
        });
//        Toast.makeText(getActivity(), arvBin.getPreOrdem(), Toast.LENGTH_SHORT).show();

        return view;
    }
    private void configurarSpinner(ArvBin arvBin){
        String[] items = new String[]{"Left", "Right"};

        String[] nos = arvBin.getPreOrdem().split(" ");
        ArrayList<Integer> parents = new ArrayList<>();
        for(String s : nos){
            int v = Integer.parseInt(s);
            if(arvBin.getEquerda(v) == -1 || arvBin.getDireita(v) == -1) {
                parents.add(v);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner_position.setAdapter(adapter);
        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, parents);
        spinner_position.setAdapter(adapter);
        spinner_parent.setAdapter(adapter2);
    }
    private void createTreeDialog(final LayoutInflater inflater, final ViewGroup container, final ArvBin arvBin){
        View viewDialog = inflater.inflate(R.layout.tree_add, container, false);
        spinner_position = viewDialog.findViewById(R.id.spinner_position);
        spinner_parent = viewDialog.findViewById(R.id.spinner_parent);
        final EditText newChild = viewDialog.findViewById(R.id.et_new_child_id);
        configurarSpinner(arvBin);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setView(viewDialog);
        dialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if(!newChild.getText().toString().equals("")){
                        int parent = Integer.parseInt(spinner_parent.getSelectedItem().toString());
                        int child = Integer.parseInt(newChild.getText().toString());
                        if(child>=0){
                            if(spinner_position.getSelectedItemPosition() == 0) {
                                if(arvBin.insereEsq(parent, child))
                                    Toast.makeText(getActivity(), child+" inserido a esquerda de "+parent+"!", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(getActivity(), "Falha ao inserir elemento na arvore!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if(arvBin.insereDir(parent, child))
                                    Toast.makeText(getActivity(), child+" inserido a direita de "+parent+"!", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(getActivity(), "Falha ao inserir elemento na arvore!", Toast.LENGTH_SHORT).show();
                            }
                            configuraArvore(arvBin, view);
                        }else{
                            Toast.makeText(getActivity(), "Valor negativo não permitido!", Toast.LENGTH_SHORT).show();
                            createTreeDialog(inflater, container, arvBin);
                        }
                    }else {
                        Toast.makeText(getActivity(), "Por favor inserir todos os campos!", Toast.LENGTH_SHORT).show();
                        createTreeRootDialog(inflater, container, arvBin);
                    }
                }catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Valor inválido", Toast.LENGTH_SHORT).show();
                }


            }
        });
        dialog.create();
        dialog.show();
    }
    private void createTreeRootDialog(final LayoutInflater inflater, final ViewGroup container, final ArvBin arvBin){
        View viewDialog = inflater.inflate(R.layout.tree_root_add, container, false);
        final EditText newRoot = viewDialog.findViewById(R.id.et_new_root_id);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setView(viewDialog);
        dialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {

                    if(!newRoot.getText().toString().equals("")){
                        int root = Integer.parseInt(newRoot.getText().toString());
                        if(root>=0){
                            if(arvBin.insereRaiz(root)) {
                                Toast.makeText(getActivity(), "Inserido " + root + " na raiz", Toast.LENGTH_LONG).show();
                                configuraArvore(arvBin, view);
                            }
                            else
                                Toast.makeText(getActivity(), "Falha ao inserir raiz", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getActivity(), "Valor negativo não permitido!", Toast.LENGTH_SHORT).show();
                            createTreeRootDialog(inflater, container, arvBin);
                        }
                    }else {
                        Toast.makeText(getActivity(), "Por favor inserir todos os campos!", Toast.LENGTH_SHORT).show();
                        createTreeRootDialog(inflater, container, arvBin);
                    }
                }catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Valor inválido", Toast.LENGTH_SHORT).show();
                }


            }
        });
        dialog.create();
        dialog.show();
    }
    private void configuraArvore(ArvBin arvBin, int valor, TreeNode node, int margin, int busca){

        TreeNode esq = null;
        TreeNode dir = null;
        int elemento = arvBin.getEquerda(valor);
        if(elemento != -1){
            //Child
            int layout = R.layout.child_left;
            if(elemento == busca) layout = R.layout.child_left_found;
            MyHolder.IconTreeItem childItem = new MyHolder.IconTreeItem(R.drawable.child, ""+elemento);
            esq = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, layout, margin));
        }

        elemento = arvBin.getDireita(valor);
        if(elemento != -1){
            //Child
            int layout = R.layout.child_right;
            if(elemento == busca) layout = R.layout.child_right_found;
            MyHolder.IconTreeItem childItem = new MyHolder.IconTreeItem(R.drawable.child, ""+elemento);
            dir = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, layout, margin));
        }
        if(esq!= null){
            node.addChild(esq);
        }
        if(dir!= null)
            node.addChild(dir);
        if(arvBin.getEquerda(valor) != -1){
            configuraArvore(arvBin, arvBin.getEquerda(valor), esq, margin+PLUSMARGIN, busca);
        }
        if(arvBin.getDireita(valor) != -1){
            configuraArvore(arvBin, arvBin.getDireita(valor), dir, margin+PLUSMARGIN, busca);
        }
    }
    private void configuraArvore(ArvBin arvBin, int valor, TreeNode node, int margin){

        TreeNode esq = null;
        TreeNode dir = null;
        int elemento = arvBin.getEquerda(valor);
        if(elemento != -1){
            //Child
            int layout = R.layout.child_left;
            MyHolder.IconTreeItem childItem = new MyHolder.IconTreeItem(R.drawable.child, ""+elemento);
            esq = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, layout, margin));
        }

        elemento = arvBin.getDireita(valor);
        if(elemento != -1){
            //Child
            int layout = R.layout.child_right;
            MyHolder.IconTreeItem childItem = new MyHolder.IconTreeItem(R.drawable.child, ""+elemento);
            dir = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, layout, margin));
        }
        if(esq!= null){
            node.addChild(esq);
        }
        if(dir!= null)
            node.addChild(dir);
        if(arvBin.getEquerda(valor) != -1){
            configuraArvore(arvBin, arvBin.getEquerda(valor), esq, margin+PLUSMARGIN);
        }
        if(arvBin.getDireita(valor) != -1){
            configuraArvore(arvBin, arvBin.getDireita(valor), dir, margin+PLUSMARGIN);
        }
    }

    private void configuraArvore(ArvBin arvBin, View view){
        ((LinearLayout) view.findViewById(R.id.ll_parent)).removeAllViews();

        int margin = 120;
        TreeNode root = TreeNode.root();
        int valor = arvBin.getRaiz();
        TreeNode parent = null;
        if(valor != -1){
            //Parent
            MyHolder.IconTreeItem nodeItem = new MyHolder.IconTreeItem(R.drawable.root, ""+valor);
            parent = new TreeNode(nodeItem).setViewHolder(new MyHolder(getContext(), true, MyHolder.DEFAULT, MyHolder.DEFAULT));
        }else {
            return;
        }
        TreeNode esq = null;
        TreeNode dir = null;
        int elemento = arvBin.getEquerda(valor);
        if(elemento != -1){
            //Child
            MyHolder.IconTreeItem childItem = new MyHolder.IconTreeItem(R.drawable.child, ""+elemento);
            esq = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, R.layout.child_left, margin));
        }
        elemento = arvBin.getDireita(valor);
        if(elemento != -1){
            //Child
            MyHolder.IconTreeItem childItem = new MyHolder.IconTreeItem(R.drawable.child, ""+elemento);
            dir = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, R.layout.child_right, margin));
        }
        if(esq!= null)
            parent.addChild(esq);

        if(dir!= null) {
            parent.addChild(dir);
        }
        if(arvBin.getEquerda(valor) != -1){
            configuraArvore(arvBin, arvBin.getEquerda(valor), esq, margin+PLUSMARGIN);
        }
        if(arvBin.getDireita(valor) != -1){
            configuraArvore(arvBin, arvBin.getDireita(valor), dir, margin+PLUSMARGIN);
        }

        root.addChild(parent);
        //Add AndroidTreeView into view.
        AndroidTreeView tView = new AndroidTreeView(getContext(), root);
        ((LinearLayout) view.findViewById(R.id.ll_parent)).addView(tView.getView());
        tView.expandAll();
    }
    private void configuraArvore(ArvBin arvBin, View view, int busca){
        ((LinearLayout) view.findViewById(R.id.ll_parent)).removeAllViews();

        int margin = 120;
        TreeNode root = TreeNode.root();
        //Parent
        int layoutRoot = R.layout.root;
        if(busca == arvBin.getRaiz()) layoutRoot = R.layout.root_found;
        MyHolder.IconTreeItem nodeItem = new MyHolder.IconTreeItem(R.drawable.root, ""+arvBin.getRaiz());
        TreeNode parent = new TreeNode(nodeItem).setViewHolder(new MyHolder(getContext(), true, layoutRoot, MyHolder.DEFAULT));

        int valor = arvBin.getRaiz();

        TreeNode esq = null;
        TreeNode dir = null;
        int elemento = arvBin.getEquerda(valor);
        if(elemento != -1){
            //Child
            int layout = R.layout.child_left;
            if(elemento == busca) layout = R.layout.child_left_found;
            MyHolder.IconTreeItem childItem = new MyHolder.IconTreeItem(R.drawable.child, ""+elemento);
            esq = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, layout, margin));
        }
        elemento = arvBin.getDireita(valor);
        if(elemento != -1){
            //Child
            int layout = R.layout.child_right;
            if(elemento == busca) layout = R.layout.child_right_found;
            MyHolder.IconTreeItem childItem = new MyHolder.IconTreeItem(R.drawable.child, ""+elemento);
            dir = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, layout, margin));
        }
        if(esq!= null)
            parent.addChild(esq);

        if(dir!= null) {
            parent.addChild(dir);
        }
        if(arvBin.getEquerda(valor) != -1){
            configuraArvore(arvBin, arvBin.getEquerda(valor), esq, margin+PLUSMARGIN, busca);
        }
        if(arvBin.getDireita(valor) != -1){
            configuraArvore(arvBin, arvBin.getDireita(valor), dir, margin+PLUSMARGIN, busca);
        }

        root.addChild(parent);
        //Add AndroidTreeView into view.
        AndroidTreeView tView = new AndroidTreeView(getContext(), root);
        ((LinearLayout) view.findViewById(R.id.ll_parent)).addView(tView.getView());
        tView.expandAll();
    }

    private void configTreeTeste(View view){
        //Root
        TreeNode root = TreeNode.root();

        //Parent
        MyHolder.IconTreeItem nodeItem = new MyHolder.IconTreeItem(R.drawable.root, "Parent");
        TreeNode parent = new TreeNode(nodeItem).setViewHolder(new MyHolder(getContext(), true, MyHolder.DEFAULT, MyHolder.DEFAULT));

        //Child
        MyHolder.IconTreeItem childItem = new MyHolder.IconTreeItem(R.drawable.child, "Child");
        TreeNode child = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, R.layout.child_left, 40));
        //Sub Child
        TreeNode child2 = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, R.layout.child_left, 40));

        //Sub Child
        TreeNode subChild = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, R.layout.child_left, 80));

        //Sub Child
        TreeNode subChild4 = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, R.layout.child_left, 80));


        //Sub Child
        TreeNode subChild2 = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, R.layout.child_left, 100));

        //Sub Child
       TreeNode subChild3 = new TreeNode(childItem).setViewHolder(new MyHolder(getContext(), false, R.layout.child_left, 120));


        //Add sub child_left.
        child.addChild(subChild);
        subChild.addChild(subChild2);
        child.addChild(subChild4);
        subChild2.addChild(subChild3);






        //Add child_left.
        parent.addChildren(child);
        parent.addChildren(child2);
        root.addChild(parent);

        //Add AndroidTreeView into view.
        AndroidTreeView tView = new AndroidTreeView(getContext(), root);
        ((LinearLayout) view.findViewById(R.id.ll_parent)).addView(tView.getView());
    }
//    private void configTree(){
//        TreeAdapterTeste treeAdapterTeste = new TreeAdapterTeste(getActivity());
//        expandableListView.setAdapter(treeAdapterTeste);
//    }

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
    @Override
    public boolean onQueryTextSubmit(String query) {
        try{
            int q = Integer.parseInt(query);

            if(arvBin.busca(q) == null){
                Toast.makeText(getContext(), "Consulta falhou", Toast.LENGTH_SHORT).show();
                configuraArvore(arvBin, view);
            }else{
                Toast.makeText(getContext(), arvBin.busca(q).getConteudo()+" encontrado!", Toast.LENGTH_SHORT).show();
                configuraArvore(arvBin, view, q);
            }
        }catch (NumberFormatException e){
            Toast.makeText(getActivity(), "Por favor insira um valor válido", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.equals(""))
            configuraArvore(arvBin, view);
        return false;
    }
}