package it.unipv.ingsw.d20.vendingmachine.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class testCatalogo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nomeFile = "localFile/beverageCatalog";
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new File(nomeFile));
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		String riga;
		String[] result = null;

		while(inputStream.hasNext()) {
			riga = inputStream.nextLine();
			result = riga.split(",");	
			for (String s :result) {
				System.out.println(s);
			}
		}
		
		VendingMachine v = new VendingMachine("id1",1000);
		v.popolaCatalogo();


	}

}
