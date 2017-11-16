package com.example.ph.projeto_final_ed.helper;

public class ListaSeq {
	private int dados[]; // Vetor que contém os dados da lista 
	private int nElementos; 
	private int tamMax;
	private boolean iniciada;
    
    public ListaSeq(){
		iniciada = false;
    }
    
    public ListaSeq(int n){
		tamMax = n;
		nElementos = 0;
		dados = new int[tamMax];
    }
    public void iniciarObjeto(int n){
		tamMax = n;
		nElementos = 0;
		dados = new int[tamMax];
		iniciada = true;
	}
    public boolean isIniciada(){
    	return iniciada;
	}

	public int tamanhoMaximo(){
    	return tamMax;
	}

    /** Verifica se a Lista está vazia */
    public boolean vazia(){
		if (nElementos == 0 )
			return true;
		else
			return false;
	}
	
    /**Verifica se a Lista está cheia */
    public boolean cheia(){
		if (nElementos == tamMax)
			return true;
		else
			return false;
	}
	
    /**Obtém o tamanho da Lista*/
    public int tamanho(){
		return nElementos;
	}
    
    /** Obtém o i-ésimo elemento de uma lista.
    		Retorna -1 se a posição for inválida. */
    public int elemento(int pos){
        
    	/* Se posição estiver fora dos limites <= 0 
         * ou > tamanho da lista */
        if ((pos > nElementos) || (pos <= 0))
            return -1;

       return dados[pos-1];
	}

    /**	Retorna a posição de um elemento pesquisado.
    		Retorna -1 caso não seja encontrado */
	public int posicao (int valor){
	    /* Procura elemento a elemento, se o dado está na
	    		lista. Se estiver, retorna a sua posição no array+1 */
	    for (int i = 0; i < nElementos; i++){
	        if (dados[i] == valor){
	            return (i + 1);
	        }
	    }

	    return -1;
	}
	
	/**	Retorna a posição de um elemento pesquisado.
	Retorna -1 caso não seja encontrado */
	public int posicao (int valor, int desloc){
		/* Procura elemento a elemento, se o dado está na
		lista. Se estiver, retorna a sua posição no array+1 */
		for (int i = desloc+1; i < nElementos; i++){
		    if (dados[i] == valor){
		        return (i + 1);
		    }
		}
		
		return -1;
	}
	
	/**Insere um elemento em uma determinada posição
    		Retorna false se a lista estiver cheia ou
    		a posição for inválida. Caso contrário retorna true */
	public boolean insere (int pos, int dado){
	    /* Verifica se a lista está cheia ou se a posicao a ser
	    inserida eh invalida (i.e., > tamanho da lista+1*/
	    if (cheia() || (pos > nElementos+1) || (pos <=0)){
	        return false;
	    }

	    /* Desloca os elementos após pos, uma posicao a
	    direita. Isso serve para abrir espaço para insercao
	    do novo elemento */
	    for (int i = nElementos; i >= pos; i--){
	 		 dados[i] = dados[i-1];
	    }

	    /* Insere o dado na posicao correta */
	    dados[pos - 1] = dado;

	 	/* Incrementa o numero de elementos na lista */
	    nElementos++;
	    return true;
	}
	
	/**Remove um elemento de uma determinada posição
    Retorna o valor do elemento removido. -1 caso a remoção falhe  */
	public int remove(int pos){
	    int dado;
		/* Verifica se a posicao eh valida */
	    if ((pos > nElementos) || (pos < 1 ))
			   return -1;

	    /* Armazena o dado a ser removido na var "dado" */
	    dado = dados[pos-1];

	    /* Desloca todos os elementos após 'pos', uma
	    posicao a esquerda */
	    for (int i = pos - 1; i < nElementos - 1; i++){
	 		  dados[i] = dados[i+1];
		 }

	   /* Decrementa o numero de elementos na lista */
	    nElementos--;
	    return dado;
	}
}
