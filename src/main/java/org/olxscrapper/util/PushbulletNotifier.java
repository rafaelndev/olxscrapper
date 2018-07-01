package org.olxscrapper.util;

import org.olxscrapper.data.Mensagem;

import com.github.sheigutn.pushbullet.Pushbullet;
import com.github.sheigutn.pushbullet.exception.PushbulletApiException;
import com.github.sheigutn.pushbullet.items.push.sendable.SendablePush;
import com.github.sheigutn.pushbullet.items.push.sendable.defaults.SendableNotePush;

public class PushbulletNotifier implements Runnable {
	
	private Mensagem mensagem;
	
	public PushbulletNotifier(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public void run() {
		String pushbulletKey = "";
		
		pushbulletKey = GetProperties.getInstance().getPushbulletKey();

		Pushbullet pb = new Pushbullet(pushbulletKey);
		SendablePush newPush = new SendableNotePush(mensagem.getTitulo(), mensagem.getMsgBody());
		try {
			
			pb.push(newPush);
		} catch (PushbulletApiException e) {
			System.out.println("Ocorreu um erro com a chave do Pushbullet, ela pode não ter sido fornecida ou está invalida.");
			e.printStackTrace();
		} finally {
			System.out.println("Mensagem Enviada");
		}
		
	}

}
