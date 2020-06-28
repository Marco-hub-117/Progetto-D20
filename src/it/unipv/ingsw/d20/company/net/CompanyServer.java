package it.unipv.ingsw.d20.company.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Questa classe si occupa di accettare nuove connessioni da parte delle 
 * macchinette. Quando una macchinetta tenta di connettersi viene fatto
 * partire un nuovo thread per la sua gestione.
 *
 */
public class CompanyServer extends Thread{
	
	private int port;

	public CompanyServer(int port) {
		this.port = port;
	}

	/**
	 * Permette di accettare continuamente nuove connessioni.
	 * @param port numero della socket
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while (true) {
				Socket socket = serverSocket.accept();
				ConnectedClient client = new ConnectedClient(socket);
				client.start();
			}
		} catch (IOException ie) {
			
		}
	}

}
