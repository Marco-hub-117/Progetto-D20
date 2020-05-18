package it.unipv.ingsw.d20.company.model.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CompanyServer {

	@SuppressWarnings("resource")
	public void serverLoop(int port) throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		while (true) {
			Socket socket = serverSocket.accept();
			ConnectedClient client = new ConnectedClient(socket);
			client.start();
		}		
	}

}
