package org.olxscrapper.util;

import org.olxscrapper.data.Mensagem;

import com.github.sheigutn.pushbullet.Pushbullet;
import com.github.sheigutn.pushbullet.items.push.sendable.SendablePush;
import com.github.sheigutn.pushbullet.items.push.sendable.defaults.SendableNotePush;

public class PushbulletNotifier implements Runnable {
	
	private Mensagem mensagem;
	
	public PushbulletNotifier(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public void run() {
		System.out.println("Teste Pushbullet");
		String apiToken = "o.la5tSSKTyH36DeVvDSHpmVFl3BVhWUdQ";
		Pushbullet pb = new Pushbullet(apiToken);
		SendablePush newPush = new SendableNotePush(mensagem.getTitulo(), mensagem.getMsgBody());
		pb.push(newPush);
	}

}
