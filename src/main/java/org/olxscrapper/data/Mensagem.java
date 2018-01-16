package org.olxscrapper.data;

public class Mensagem {

	private String titulo;
	private String msgBody;
	
	public Mensagem(String titulo, String msgBody) {
		this.titulo = titulo;
		this.msgBody = msgBody;
	}


	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
