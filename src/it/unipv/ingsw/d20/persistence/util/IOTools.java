package it.unipv.ingsw.d20.persistence.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class IOTools {
	
	public static String getVendingType() {
		String fileName = "localFile/vendingType";
		
		String type = null;
		
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(fileName));
		
			type = in.readLine();
			
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return type;
	}
	
	public static String getVendingID() {
		String fileName = "localFile/vendingID_id1";
		
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

	public static void setVendingID(String IDNumber) {
		String fileName = "localFile/vendingID_id1";
		
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(fileName));
			
			out.println(IDNumber);
			
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
