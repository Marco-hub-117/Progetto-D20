package it.unipv.ingsw.d20.vendingmachine.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import it.unipv.ingsw.d20.vendingmachine.model.net.VendingMachineClient;

public class VendingMachineLauncher {

	public static void main(String[] args) {
		String IDNumber = IDNumberReader();
		
		try {
			VendingMachineClient client = new VendingMachineClient();
		
			if (IDNumber.equals("")) {
				IDNumber = client.firstConnectionToServer();
				IDNumberPrinter(IDNumber);
				System.out.println("IDNumber printed");
			} else {
				System.out.println(client.connectToServer(IDNumber));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void IDNumberPrinter(String IDNumber) {
		String fileName = "assets/ID_number.txt";
		
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(fileName));
			
			out.println(IDNumber);
			
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}

	private static String IDNumberReader() {
		String fileName = "assets/ID_number.txt";
		
		String IDNumber = null;
		
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(fileName));
		
			IDNumber = in.readLine();
			
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		if (IDNumber == null) {
			IDNumber = "";
		}
		
		return IDNumber;
	}

}
