package it.unipv.ingsw.d20.util.persistence.local;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import it.unipv.ingsw.d20.vendingmachine.model.Constants;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Tank;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.CashContainer;

public class VendingLocalIO {

	public VendingLocalIO() {}
	
	public BeverageCatalog getCatalogFromLocal() {
		BeverageCatalog bvCatalog = new BeverageCatalog();
		String nomeFile = Constants.FILEPATH + Constants.BVCATPATH;
		Scanner inputStream = null;
		
		try {
			inputStream = new Scanner(new BufferedReader(new FileReader(nomeFile)));
			String riga;
			String[] result = null;

			while(inputStream.hasNext()) {
				riga = inputStream.nextLine();
				result = riga.split(",");
				BeverageDescription bvdesc = new BeverageDescription(result[0],result[1],Double.valueOf(result[2]));
				for (int i = 3;i<result.length;i= i+2) {
					bvdesc.addIngredient(Ingredients.valueOf(result[i]),Double.valueOf(result[i+1]));
				}
				bvCatalog.addBeverageDescription(bvdesc);
				result = null;
			}
			
		} catch(FileNotFoundException e) {
			System.out.println(e);
		} finally {
			if (inputStream != null)
				inputStream.close();
		}
		
		return bvCatalog;
	}

	public void saveCatalogIntoLocal (BeverageCatalog bvCatalog) {
		String nomeFile = Constants.FILEPATH + Constants.BVCATPATH;
		try {
			FileWriter myWriter = new FileWriter(nomeFile);
			PrintWriter myPrintWriter   = new PrintWriter(myWriter);
			for (String code : bvCatalog.getCatalog().keySet()) {
				BeverageDescription bd = bvCatalog.getBeverageDesc(code);
				String line = bd.getCode()+","+bd.getName()+","+bd.getPrice();
				Map<Ingredients,Double> ingr = bd.getIngredients();
				for (Ingredients key : ingr.keySet()) {
					line +=","+key+","+ingr.get(key);
				}
				myPrintWriter.println(line);
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} 
	}

	public HashMap<Ingredients,Tank> getTanksFromLocal() {
		HashMap<Ingredients,Tank> tankList = new HashMap<>();
		String nomeFile = Constants.FILEPATH + Constants.TANKSPATH;
		Scanner inputStream = null;
		
		try {
			inputStream = new Scanner(new BufferedReader(new FileReader(nomeFile)));
			String riga;
			String[] result = null;

			while(inputStream.hasNext()) {
				riga = inputStream.nextLine();
				result = riga.split(",");
				Tank t = new Tank(Ingredients.valueOf(result[0]),Double.valueOf(result[1]),Double.valueOf(result[2]));
				if (!(tankList.containsKey(t.getId()))) 
					tankList.put(t.getId(), t);
				result = null;
			}

		} catch(FileNotFoundException e) {
			System.out.println(e);
		} finally {
			if (inputStream != null)
				inputStream.close();
		}
		return tankList;
	}
	
	public void saveTankIntoLocal(HashMap<Ingredients,Tank> tankList) {
		String nomeFile = Constants.FILEPATH + Constants.TANKSPATH;
		try {
			FileWriter myWriter = new FileWriter(nomeFile);
			PrintWriter myPrintWriter   = new PrintWriter(myWriter);
			for (Ingredients i : tankList.keySet()) {
				Tank t = tankList.get(i);
				String line = t.getId()+","+t.getLevel()+","+t.getTemperature();
				myPrintWriter.println(line);
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} 
	}
	
	public String getVendingIDFromLocal() {
		String fileName = Constants.FILEPATH + Constants.VENDINGPATH;
		
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
	
	public void saveVendingIDIntoLocal(String IDNumber) {
		String fileName = Constants.FILEPATH + Constants.VENDINGPATH;
		
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(fileName));
			
			out.println(IDNumber);
			
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public String getVendingTypeFromLocal() {
		String fileName = Constants.FILEPATH + Constants.VENDINGTYPEPATH;
		
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
	
	public CashContainer getCashContainerFromLocal() {
		int[] cashQuantity = new int[6];
		
		String nomeFile = Constants.FILEPATH + Constants.CASHCONTAINERSTATUSPATH;
		Scanner inputStream = null;
		
		try {
			inputStream = new Scanner(new BufferedReader(new FileReader(nomeFile)));

			for (int i = 0; i < 6; i++) {
				cashQuantity[i] = inputStream.nextInt();
			}

		} catch(FileNotFoundException e) {
			System.out.println(e);
		} finally {
			if (inputStream != null)
				inputStream.close();
		}
		
		return new CashContainer(cashQuantity);
	}
	
	public void saveCashContainerIntoLocal(CashContainer cashContainer) {
		String nomeFile = Constants.FILEPATH + Constants.CASHCONTAINERSTATUSPATH;
		
		try {
			FileWriter myWriter = new FileWriter(nomeFile);
			PrintWriter myPrintWriter   = new PrintWriter(myWriter);
			
			for (int i : cashContainer.getCoinNumber()) {
				myPrintWriter.println(i);
			}
			
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} 
	}
	
}

