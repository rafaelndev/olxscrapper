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
	
	private GetProperties() throws IOException {
		try {
			Properties prop = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Arquivo de configuração " + CONFIG_FILE + "' não foi encontrado");
			}

			pushbullet_key = prop.getProperty("pushbullet_key");

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}

	}
	
	public static GetProperties getInstance() throws IOException {
		if (INSTANCIA == null) {
			INSTANCIA = new GetProperties();
		}
		return INSTANCIA;
	}

	public String getPushbulletKey() {
		return pushbullet_key;
	}

}
