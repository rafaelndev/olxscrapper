package org.olxscrapper.data;

import java.io.Serializable;

public class OlxItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	private String title;
	private String detalhe;
	private String regiao;
	private String preco;
	public String data;

	public OlxItem(String url, String title, String detalhe, String regiao, String preco, String data) {
		this.url = url;
		this.title = title;
		this.detalhe = detalhe;
		this.regiao = regiao;
		this.preco = preco;
		this.data = data;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	
	@Override
	public String toString() {
		return String.format("%s \n %s - %s \n %s \n %s \n \n %s", this.getTitle(), this.getDetalhe(), this.getRegiao(), this.getPreco(), this.getData(), this.getUrl());
	}
}
