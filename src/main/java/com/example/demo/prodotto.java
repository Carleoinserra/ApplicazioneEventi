package com.example.demo;

public class prodotto {
	
	
	String nome;
	String tipologia;
	double prezzo;
	int postiDisponibili;
	String data;
	String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public int getPostiDisponibili() {
		return postiDisponibili;
	}
	public void setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "prodotto [nome=" + nome + ", tipologia=" + tipologia + ", prezzo=" + prezzo + ", postiDisponibili="
				+ postiDisponibili + ", data=" + data + ", url=" + url + "]";
	}
	
	

}
