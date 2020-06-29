package it.unipv.ingsw.d20.company.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Questa classe si occupa di accettare nuove connessioni dai
 * distributori automatici. Quando un distributore tenta di connettersi viene fatto
 * partire un nuovo thread per la sua gestione.
 *
 */
public class CompanyServer extends Thread {
	
	private int port;

	/**
	 * Il costruttore istanzia solamente il numero di porta
	 * @param numero di porta
	 */
	public CompanyServer(int port) {
		this.port = port;
	}

	/**
	 * Permette di accettare continuamente nuove connessioni.
	 */
	@Override
	public void run() {
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(port);
			
			while (true) {
				Socket socket = serverSocket.accept();
				ConnectedClient client = new ConnectedClient(socket);
				client.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
