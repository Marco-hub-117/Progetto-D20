package it.unipv.ingsw.d20.company.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import it.unipv.ingsw.d20.company.Company;
import it.unipv.ingsw.d20.company.VendingMachineInfo;

/**
 * Questa classe gestisce la connessione con un distributore,
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
	 * Riceve una stringa dal distributore e la gestisce. Se il messaggio contiene solo una cifra
	 * significa che la macchinetta è stata accesa per la prima volta e sta inviando il suo tipo per essere registrata, 
	 * altrimenti sta notificando le sue informazioni alla company.
	 */
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			String vmMessage = in.readLine();
			if (vmMessage != null) { //se il messaggio ricevuto � nullo non fa niente e chiude la socket
				String[] msgParts = vmMessage.split("/"); //divide il messagio in pi� parti
			
				if (msgParts.length == 1) { 
					out.println(Company.registerNewVendingMachine(vmMessage)); //registra la nuova vending e le restituisce il suo ID
				} else if (msgParts.length == 4) {
					if (Company.isSetpointModified(msgParts[0])) { //ci sono aggiornamenti da inviare
						out.println(Company.getVendingInfo(msgParts[0]).getUpdatedTemps()); //manda le nuove temperature alla vending
					}
					else {
						out.println(""); //manda un messaggio vuoto: non ci sono aggiornamenti
						Company.vendingMachineInfoList.replace(msgParts[0], new VendingMachineInfo(msgParts[1], msgParts[2], msgParts[3])); //aggiorna le info della vm che ha mandato il messaggio
					}
					Company.setSetpointModified(msgParts[0], false); //fatto l'aggiornamento, reimposta il boolean a falso
				}
			}
        
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
