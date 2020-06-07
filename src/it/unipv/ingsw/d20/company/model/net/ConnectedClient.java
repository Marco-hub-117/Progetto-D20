package it.unipv.ingsw.d20.company.model.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.unipv.ingsw.d20.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.persistence.vending.VendingPOJO;

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
			 * facendole poi sapere i dati necessari ad inizializzarla (ID).
			 * Se la stringa ricevuta contiene l'ID della vending machine, verrà utilizzata
			 * come notifica per settarne lo status sul database.
			 */
			String vmRequest = in.readLine();
			if (vmRequest.equals("")) {
				String IDNumber = generateNewID();
				
				PersistenceFacade pf = PersistenceFacade.getInstance();
				IVendingDao ivd = pf.getVendingDao();
				ivd.addVending(new VendingPOJO(IDNumber, null));
				out.println(IDNumber);
			} else {
				//notifica che la vending è accesa
			}
        
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String generateNewID() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String vendingIDNumber = sdf.format(new Date());
		
		String fileName = "IDN" + vendingIDNumber;
		return fileName; 
	}

}
