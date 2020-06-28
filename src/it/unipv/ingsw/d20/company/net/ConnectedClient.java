package it.unipv.ingsw.d20.company.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import it.unipv.ingsw.d20.company.Company;
import it.unipv.ingsw.d20.company.VendingMachineInfo;

/**
 * Questa classe gestisce la connessione con una macchinetta,
 * nuova o esistente che sia.
 *
 */
public class ConnectedClient extends Thread {

	private Socket socket;
	
	/**
	 * Il costruttore si occupa solo di istanziare la socket della connessione.
	 * @param socket
	 */
	public ConnectedClient(Socket socket) {
		this.socket = socket;
	}
	
	/**
	 * Riceve una stringa dalla macchinetta e la gestisce. Se il messaggio coontiene solo una cifra
	 * significa che la macchinetta è stata accesa per la prima volta e invia il suo tipo per essere registrata, 
	 * altrimenti notifica il suo status alla company.
	 */
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			String vmMessage = in.readLine();
			if (vmMessage != null) {
				String[] msgParts = vmMessage.split("/");
			
				if (vmMessage.length() == 1) {
					out.println(Company.registerNewVendingMachine(vmMessage)); //registra la nuova vending e le restituisce il suo ID
				} else if (msgParts.length == 4) {
					Company.vendingMachineInfoList.replace(msgParts[0], new VendingMachineInfo(msgParts[1], msgParts[2], msgParts[3])); //aggiorna l'ora dell'ultimo update della vm che ha mandato il messaggio
				}
			}
        
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
