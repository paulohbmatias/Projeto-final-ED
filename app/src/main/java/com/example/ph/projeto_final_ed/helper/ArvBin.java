package com.example.ph.projeto_final_ed.helper;

import android.util.Log;
import android.widget.Toast;

public class ArvBin {
	private NoTree raiz;

	public ArvBin(){
		raiz = null;
	}
	
	/** Verifica se a árvore está vazia */
	public boolean vazia (){
		return (raiz == null);
	}

	/** Funcao de busca recursiva.
		Retorna o endereço do elemento se ele for encontrado.
		Caso contrario, retorna null*/
	private NoTree busca(NoTree T, int valor) {
		NoTree aux;
		
		// Condicao de parada
		if (T == null) 
			return null;  // Arvore Vazia

		// Condicao de parada
		if(T.getConteudo() == valor) 
			return T; //Elem. encontrado na raiz
	  
		// Caso recursivo
		aux = busca(T.getEsq(), valor);
		if (aux == null) 
			aux = busca(T.getDir(), valor);
	   
		return aux;
	}
	
	/** Buscar um elemento na árvore
		Retorna o endereço se o elemento for encontrado, 
		Caso contrário retorna NULL*/
	public NoTree busca(int valor) {
		if (vazia())
			return null;
		
		//No res = busca(raiz, valor);
		//return res;
		return busca(raiz, valor);
	}
	
	
	/** Insere um nó raiz em uma árvore vazia 
	 	Retorna true se a inserção for com sucesso.
		Caso contrário, retorna false */   
	public boolean insereRaiz(int valor) {   
		if (raiz != null) 
			return false;  //Erro: Arvore não está vazia

		NoTree novoNo = new NoTree();
		novoNo.setConteudo(valor);
		novoNo.setEsq(null);
		novoNo.setEsq(null);
		
		raiz = novoNo;
		return true;
	}   

	/** Insere um filho à direita de um dado nó.
    		Retorna true se a inserção for com sucesso,
    		Caso contrário false  */
	public boolean insereDir(int vPai, int vFilho ) {
		
		// Verifica se o elemento já existe
		NoTree filho = busca(vFilho);
		if (filho != null)
	        return false;  // Err: dado já existe
	
		// Busca o pai e verifica se possui filho direito
		NoTree pai = busca(vPai);
	  	if (pai == null)
			return false;  // Err: pai não encontrado
		
	  	if (pai.getDir() != null)
			return false;  // Err: filho dir já existe
	
		NoTree novoNo = new NoTree();
		novoNo.setConteudo(vFilho);
		novoNo.setEsq(null);
		novoNo.setDir(null);
		
		pai.setDir(novoNo);
		
		return true;
	}

	/** Insere um filho à esquerda de um dado nó.
		Retorna true se a inserção for com sucesso,
		Caso contrário false  */
	public boolean insereEsq(int vPai, int vFilho ) {
		
		// Verifica se o elemento já existe 
		NoTree filho = busca(vFilho);
		if (filho != null)
	        return false;  // Err: dado já existe
	
		// Busca o pai e verifica se possui filho da esq
		NoTree pai = busca(vPai);
	  	if (pai == null)
			return false; // Err: pai não encontrado
	  	
		if (pai.getEsq() != null)
			return false; // Err: filho esq já existe
	
		NoTree novoNo = new NoTree();
		novoNo.setConteudo(vFilho);
		novoNo.setEsq(null);
		novoNo.setDir(null);
		
		pai.setEsq(novoNo);
		return true;
	}

	/** Exibe o conteúdo de uma árvore em pré-ordem*/
	private void exibePreOrdem(NoTree T) {
		if (T == null)
			return;

		Log.i("tree"," " + T.getConteudo());
		if (T.getEsq() != null)
			exibePreOrdem(T.getEsq());
	
	 	if (T.getDir() != null)
			exibePreOrdem(T.getDir());
	}

	/** Exibe o conteúdo de uma árvore em pré-ordem*/
	public void exibePreOrdem() {
		if (raiz == null)
            Log.i("tree","Arvore vazia");
		else
			exibePreOrdem(raiz);
	}
    private String getPreOrdem(NoTree T, String s){
        if (T == null)
            return s;

        s += T.getConteudo();
        if (T.getEsq() != null)
            s=getPreOrdem(T.getEsq(), s);

        if (T.getDir() != null)
            return getPreOrdem(T.getDir(), s);

		return s;
	}
	public String getPreOrdem(){
        String s = "";
        if (raiz == null)
            return "";
        else
            return getPreOrdem(raiz, s);
    }
	
	
	/** Exibe o conteúdo de uma árvore em pré-ordem*/
	private void exibeInOrdem(NoTree T) {
		if (T == null)
			return ;

		if (T.getEsq() != null)
			exibeInOrdem(T.getEsq());
	
	    System.out.print(" " + T.getConteudo());
	
	 	if (T.getDir() != null)
			exibeInOrdem(T.getDir());
	}

	
	/** Exibe o conteúdo de uma árvore em pré-ordem*/
	public void exibeInOrdem() {
		if (raiz == null)
			System.out.println("Arvore vazia");
		else
			exibeInOrdem(raiz);
	}	
	
	/** Exibe o conteúdo de uma árvore em pré-ordem*/
	private void exibePosOrdem(NoTree T) {
		if (T == null)
			return ;

		if (T.getEsq() != null)
			exibePosOrdem(T.getEsq());
	
	 	if (T.getDir() != null)
			exibePosOrdem(T.getDir());

	 	System.out.print(" " + T.getConteudo());
	}
	
	/** Exibe o conteúdo de uma árvore em pré-ordem*/
	public void exibePosOrdem() {
		if (raiz == null)
			System.out.println("Arvore vazia");
		else
			exibePosOrdem(raiz);
	}
	public int getRaiz(){
		return raiz.getConteudo();
	}
	public int getEquerda(int valor){
        if (vazia())
            return -1;

        //No res = busca(raiz, valor);
        //return res;
        if(busca(raiz, valor).getEsq() != null){
        	return busca(raiz, valor).getEsq().getConteudo();
		}else{
        	return -1;
		}
    }
    public int getDireita(int valor){
        if (vazia())
            return -1;

        //No res = busca(raiz, valor);
        //return res;
		if(busca(raiz, valor).getDir() != null){
			return busca(raiz, valor).getDir().getConteudo();
		}else{
			return -1;
		}
    }
	
}
