package org.olxscrapper.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.olxscrapper.util.Hash;

@Entity
@Table(name="OlxItem")
public class OlxItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(length=60, nullable=false)
	private String url;

	@Column(length=30, nullable=false)
	private String title;

	@Column(length=100, nullable=false)
	private String detalhe;

	@Column(length=20, nullable=false)
	private String regiao;

	@Column(length=10, nullable=false)
	private String preco;

	@Column(length=20, nullable=false)
	public String data;
	
	@Column(length=64, nullable=false)
	public String checksum;

	public OlxItem(String url, String title, String detalhe, String regiao, String preco, String data) {
		this.url = url;
		this.title = title;
		this.detalhe = detalhe;
		this.regiao = regiao;
		this.preco = preco;
		this.data = data;

		String checksum = Hash.MD5.checksum(this.getUrl());
		this.checksum = checksum;
	}
	
	public OlxItem() {}

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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChecksum() {
		return checksum;
	}

	@Override
	public String toString() {
		return String.format("%s \n %s - %s \n %s \n %s \n \n %s", this.getTitle(), this.getDetalhe(), this.getRegiao(), this.getPreco(), this.getData(), this.getUrl());
	}
}
