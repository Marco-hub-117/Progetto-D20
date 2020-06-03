package it.unipv.ingsw.d20.vendingmachine.model.beverage;
//hh
import java.util.HashMap;
import java.util.Map;

public class BeverageCatalog {
	
	private Map<String, BeverageDescription> catalog;
	
	public BeverageCatalog() {
		catalog = new HashMap<String, BeverageDescription>();
	}
	public BeverageDescription getBeverageDesc(String code) { //DATO LA STRINGA RESTITUISCE L'OGGETTO CORRISPONDENTE NELLA MAPPA
		if(catalog.containsKey(code)) {	
			return catalog.get(code);
		} else {
			return null;
		}
	}
	public void setIngredient(String code, Ingredients i, Double q) {
		if(catalog.containsKey(code)) {
			catalog.get(code).setIngredientQuantity(i, q);
		}
	}
	
	public void addBeverageDescription(BeverageDescription b) {//AGGIUNGE UNA NUOVA BEVERAGE DESCRIPTION NELLA MAPPA
		catalog.put(b.getCode(), b);
	}
	
	public Map<String, BeverageDescription> getCatalog() { //METODO GET DI CATALOG
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


}