package com.example.ph.projeto_final_ed.helper;

public class NoTree {
	private int conteudo;
	private NoTree esq;
	private NoTree dir;
	
	public NoTree(){
		esq = null;
		dir = null;
	}
	
	public int getConteudo() {
		return conteudo;
	}
	public void setConteudo(int conteudo) {
		this.conteudo = conteudo;
	}
	
	public NoTree getEsq() {
		return esq;
	}
	public void setEsq(NoTree esq) {
		this.esq = esq;
	}
	
	public NoTree getDir() {
		return dir;
	}
	
	public void setDir(NoTree dir) {
		this.dir = dir;
	}
}
