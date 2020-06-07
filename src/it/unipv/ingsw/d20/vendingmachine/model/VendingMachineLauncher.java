package it.unipv.ingsw.d20.vendingmachine.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import it.unipv.ingsw.d20.persistence.util.IOTools;
import it.unipv.ingsw.d20.vendingmachine.model.net.VendingMachineClient;

public class VendingMachineLauncher {

	public static void main(String[] args) {
		String IDNumber = IOTools.getVendingID();
		
		try {
			VendingMachineClient client = new VendingMachineClient();
		
			if (IDNumber.equals("")) {
				IDNumber = client.firstConnectionToServer();
				IOTools.setVendingID(IDNumber);
				System.out.println("IDNumber printed");
			} else {
				System.out.println(client.connectToServer(IDNumber));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
