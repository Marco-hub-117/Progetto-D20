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
	 * Riceve una stringa dalla macchinetta e la gestisce. Se il messaggio è vuoto
	 * significa che la macchinetta è stata accesa per la prima volta, altrimenti
	 * è solo una notifica del suo status.
	 */
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			/*
			 * Se la stringa ricevuta dalla vending machine (client) è vuota, significa
			 * che è la prima volta che si connette e bisogna aggiungerla al data base,
			 * facendole poi sapere l'ID che le è stato assegnato.
			 * Se la stringa ricevuta contiene l'ID della vending machine e altre informazioni, verrà utilizzata
			 * come notifica per settarne lo status sul database.
			 */
			String vmMessage = in.readLine();
			String[] msgParts = vmMessage.split("	");
					
			if (vmMessage.equals("")) {
				out.println(Company.registerNewVendingMachine()); //registra la nuova vending e le restituisce il suo ID
			} else if (msgParts.length == 4){
				Company.vendingMachineInfoList.replace(msgParts[0], new VendingMachineInfo(msgParts[1], msgParts[2], msgParts[3])); //aggiorna l'ora dell'ultimo update della vm che ha mandato il messaggio
			}
        
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
