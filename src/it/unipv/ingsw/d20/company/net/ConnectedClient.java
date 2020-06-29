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
	 * Riceve una stringa dalla macchinetta e la gestisce. Se il messaggio contiene solo una cifra
	 * significa che la macchinetta è stata accesa per la prima volta e sta inviando il suo tipo per essere registrata, 
	 * altrimenti sta notificando le sue informazioni alla company.
	 */
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			//DA FARE: SALVARE ARRAY TEMP MODIFICATE E RIMANDARLO INDIETRO E RISETTARLO
			
			String vmMessage = in.readLine();
			if (vmMessage != null) { //se il messaggio ricevuto � nullo non fa niente e chiude la socket
				String[] msgParts = vmMessage.split("/"); //divide il messagio in pi� parti
			
				if (msgParts.length == 1) { 
					out.println(Company.registerNewVendingMachine(vmMessage)); //registra la nuova vending e le restituisce il suo ID
				} else if (msgParts.length == 4) {
					Company.vendingMachineInfoList.replace(msgParts[0], new VendingMachineInfo(msgParts[1], msgParts[2], msgParts[3])); //aggiorna le info della vm che ha mandato il messaggio
				}
			}
        
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
