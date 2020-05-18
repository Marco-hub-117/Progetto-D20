package it.unipv.ingsw.d20.company.model.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectedClient extends Thread {

	Socket socket;
	
	public ConnectedClient(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run () {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			/*
			 * Se la stringa ricevuta dalla vending machine (client) è vuota, significa
			 * che è la prima volta che si connette e bisogna aggiungerla al data base,
			 * facendole poi sapere i dati necessari ad inizializzarla (ID, catalogo...).
			 * Se la stringa ricevuta non è vuota, significa che la vending machine è già 
			 * registrata e sta inviando il suo ID per conoscere i dati necessari ad accendersi
			 * (tutti tranne l'ID).
			 */
			String vmRequest = in.readLine();
			if (vmRequest.equals("")) {
				out.println("Empty string case");
			} else {
				out.println("Vending ID case");
			}
        
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
