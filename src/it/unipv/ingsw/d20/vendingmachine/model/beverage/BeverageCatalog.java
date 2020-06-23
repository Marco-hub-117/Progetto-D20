package it.unipv.ingsw.d20.vendingmachine.model.beverage;

import java.util.HashMap;
import java.util.Map;

public class BeverageCatalog {
	
	private Map<String, BeverageDescription> catalog;
	
	public BeverageCatalog() {
		catalog = new HashMap<String, BeverageDescription>();
	}
	/**
	 * Restituisce la BeverageDescription della bevanda dato il suo codice
	 * 
	 * @param code codice della bevanda
	 * 
	 */
	public BeverageDescription getBeverageDesc(String code) {
		if(catalog.containsKey(code)) {	
			return catalog.get(code);
		} else {
			return null;
		}
	}
	/**
	 * Dato il codice di una bevanda ne imposta un ingrediente e la relativa
	 * 
	 * @param code codice della bevanda
	 * @param i ingrediente
	 * @param q quantita'
	 * 
	 * 
	 */
	public void setIngredient(String code, Ingredients i, Double q) {
		if(catalog.containsKey(code)) {
			catalog.get(code).changeQuantity(i, q);
		}
	}
	/**
	 * Aggiunge una nuova BeverageDescription
	 * 
	 * @param b bevanda da aggiungere
	 * 
	 * 
	 */
	public void addBeverageDescription(BeverageDescription b) {//aggiunge una nuova BeverageDescription
		catalog.put(b.getCode(), b);
	}
	
	public Map<String, BeverageDescription> getCatalog() {
		return catalog;
	}

	public String toString() {
		String beverageList = "";
		int c = 1;
		
		for (Map.Entry<String, BeverageDescription> i : catalog.entrySet()) {
			beverageList = beverageList + "BEVANDA_" + c + ":\n" + i.getValue().toString();
			c++;
		}
		
		/* FOR ALTERNATIVO
		 * for (BeverageDescription bd : catalog.values()) {
		 *     beverageList = beverageList + "BEVANDA_" + c + ":\n" + bd.toString();
		 *	   c++;
		 * }
		 */
		
		return beverageList;
	}
	public String toStringGui() {
		String beverageList = "";		
		for (BeverageDescription bvDesc : catalog.values()) {
			beverageList = beverageList  + bvDesc.toStringGui();	
		}
		return beverageList;
	}
}