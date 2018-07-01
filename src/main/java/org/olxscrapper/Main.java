package org.olxscrapper;

import java.io.IOException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.olxscrapper.dao.OlxItemDAO;
import org.olxscrapper.data.Mensagem;
import org.olxscrapper.data.OlxItem;
import org.olxscrapper.util.GetProperties;
import org.olxscrapper.util.PushbulletNotifier;

public class Main {

	public static void main(String[] args) {
		Document doc = null;
		OlxItemDAO olxItemDao = new OlxItemDAO();
		GetProperties propriedades = GetProperties.getInstance();

		while (true) {

			try {
				// Buscar o Documento principal de Busca
				doc = Jsoup.connect("http://ba.olx.com.br/grande-salvador/imoveis/aluguel?pe=600")
						.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1") // Precisa
						// do Agent do Desktop, se não a página mobile é acessada
						.timeout(propriedades.getJsoup_timeout() * 1000) // 30 segundos
						.get();
				// Buscar a lista principal de ads do OLX
				Element main_list = doc.select("ul#main-ad-list").first();
				// Buscar os items da lista de ads
				Elements ad_list = main_list.select("li:lt(3) > a");

				// Para Cada item da lista..
				for (Element item : ad_list) {
					String url = item.attr("href").toString();
					String titulo = item.select("h3.OLXad-list-title").first().text();
					String detalhe = item.select("p.detail-specific").first().text();
					String regiao = item.select("p.detail-region").first().text();
					String preco = item.select("p.OLXad-list-price").first().text();
					Elements data = item.select("div.col-4 > p.text");

					String strData = data.first().text() + " " + data.get(1).text();

					OlxItem newItem = new OlxItem(url, titulo, detalhe, regiao, preco, strData);

					OlxItem itemExists = olxItemDao.findByChecksum(newItem.getChecksum());
					if (itemExists != null) {
						System.out.println("Item Encontrado: " + itemExists.getTitle());
					}
					System.out.println("Item Novo: " + newItem.getTitle());
					if (itemExists == null) {
						olxItemDao.add(newItem);
						Mensagem msg = new Mensagem(newItem.getTitle(), newItem.toString());
						PushbulletNotifier pn = new PushbulletNotifier(msg);
						ExecutorService executorService = Executors.newCachedThreadPool();
						executorService.execute(pn);
					}
					try {
						Thread.sleep(propriedades.getSearch_delay() * 1000 * 60);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
