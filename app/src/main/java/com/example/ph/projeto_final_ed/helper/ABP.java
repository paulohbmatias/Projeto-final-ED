package com.example.ph.projeto_final_ed.helper;

public class ABP {
	private NoTree raiz;
	
	public ABP(){
		raiz = null;
	}
	
	/** Verifica se a árvore está vazia */
	public boolean vazia (){
		return (raiz == null);
	}

	/**Buscar recursivamente a partir da raiz.
	    Retorna o endereço se o elemento for
	    encontrado, caso contrário retorna NULL*/
	private NoTree busca(NoTree T, int valor) {
		if (T == null)
			return null;  // Arvore Vazia

		if(T.getConteudo() == valor)
			return T; 	// Elem. encontrado na raiz
		
		if (valor < T.getConteudo())
			return busca(T.getEsq(), valor);
	    else
			return busca(T.getDir(), valor);
	}
	
	/**Buscar um elemento na ABP
    		Retorna o endereço se o elemento for
    		encontrado, caso contrário retorna NULL*/
	public NoTree busca(int valor) {
		if (raiz != null) 
			return busca(raiz, valor);
		
		return null;
	}
	

	/**Exibe o conteúdo de uma árvore no formato in-ordem
	    (preserva a ordenação)*/
	private void exibe (NoTree T){
		if (T != null) {
			exibe(T.getEsq());
			System.out.print(" " + T.getConteudo());
			exibe(T.getDir());
		}
	}

	public void exibe() {
		if (raiz == null)
			System.out.println("Arvore vazia");
		else
			exibe(raiz);
	}


	
	/**Insere um nó em uma árvore ABP
	    Retorna 1 se a inserção for com sucesso.
	    Caso contrário retorna 0*/
	public boolean insere(int valor){
		if(busca(valor) != null)
			return false;

		NoTree novoNo = new NoTree();
		novoNo.setConteudo(valor);
		novoNo.setEsq(null);
		novoNo.setDir(null);

		if (raiz == null){ // Arvore vazia
	 		raiz = novoNo;
			return true;
		}



	    // Procura a posicao correta pra inserir o novo no
	    NoTree aux = raiz;
	    	NoTree p = null;
	    while (aux != null) {
	    		p = aux;
			if (valor < aux.getConteudo())
				aux = aux.getEsq();
			else
				aux = aux.getDir();
		}

		// Encontrou um nó folha para inserir
		if (valor < p.getConteudo())
			p.setEsq(novoNo);
		else
			p.setDir(novoNo);
		return true;
	}

	public int getRaiz(){
		if(vazia())
			return -1;
		else
			return raiz.getConteudo();
	}

	public int getEsquerda(int valor){
		if (vazia())
			return -1;

		//No res = busca(raiz, valor);
		//return res;
		NoTree no = busca(raiz, valor).getEsq();
		if(no != null){
			return no.getConteudo();
		}else{
			return -1;
		}
	}
	public int getDireita(int valor){
		if (vazia())
			return -1;

		//No res = busca(raiz, valor);
		//return res;
		NoTree no = busca(raiz, valor).getDir();
		if(no != null){
			return no.getConteudo();
		}else{
			return -1;
		}
	}
	private String getPreOrdem(NoTree T, String s){
		if (T == null)
			return s;

		s += T.getConteudo()+" ";
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
}
