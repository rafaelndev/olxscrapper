package org.olxscrapper.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
	private InputStream inputStream;
	private final String CONFIG_FILE = "config.properties";
	private static GetProperties INSTANCIA = null;
	
	private String pushbullet_key = "";
	private String olx_url;

	private Integer jsoup_timeout = 30; // Padrão 30 segundos
	private Integer search_delay = 300; // Padrão 5 minutos
	
	private GetProperties() {
		try {
			Properties prop = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Arquivo de configuração " + CONFIG_FILE + "' não foi encontrado");
			}

			pushbullet_key = prop.getProperty("pushbullet_key").trim();

			try {
				jsoup_timeout = Integer.valueOf(prop.getProperty("jsoup_timeout").trim());
			} catch (NumberFormatException e) {
				System.out.println("Ocorreu um erro ao ler a propriedade jsoup_timeout, verifique se o valor está correto.");
				e.printStackTrace();
			}

			try {
				search_delay = Integer.valueOf(prop.getProperty("search_delay").trim());
			} catch (NumberFormatException e) {
				System.out.println("Ocorreu um erro ao ler a propriedade search_delay, verifique se o valor está correto.");
				e.printStackTrace();
			}

			olx_url = prop.getProperty("olx_url").trim();

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static GetProperties getInstance() {
		if (INSTANCIA == null) {
			INSTANCIA = new GetProperties();
		}
		return INSTANCIA;
	}

	public String getPushbulletKey() {
		return pushbullet_key;
	}

	public Integer getJsoup_timeout() {
		return jsoup_timeout;
	}

	public Integer getSearch_delay() {
		return search_delay;
	}

	/**
	 * @return the olx_url
	 */
	public String getOlx_url() {
		return olx_url;
	}

	/**
	 * @param olx_url the olx_url to set
	 */
	public void setOlx_url(String olx_url) {
		this.olx_url = olx_url;
	}
}
