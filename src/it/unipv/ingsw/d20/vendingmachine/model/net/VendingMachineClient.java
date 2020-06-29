package it.unipv.ingsw.d20.vendingmachine.model.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Classe che consente di connettersi al server della Company.
 *
 */
public class VendingMachineClient {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	/**
	 * Costruttore della classe VendingMachineClient. 
	 * Istanzia la socket, il BufferedReader e il PrintWriter.
	 * @throws IOException
	 */
	public VendingMachineClient() throws IOException {
		socket = new Socket("localhost", 8888);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
	}
	
	/**
	 * Invia al server le informazioni attuali della macchinetta.
	 * @param info
	 * @throws IOException
	 */
	public void connectToServer(String info) throws IOException {
		out.println(info);
	}

	/**
	 * Alla prima connessione, la macchinetta invia il suo tipo alla company
	 * e viene registrata, ricevendo il suo ID.
	 * @param type
	 * @return ID
	 * @throws IOException
	 */
	public String firstConnectionToServer(String type) throws IOException {
		out.println(type);
		return in.readLine();
	}

}
