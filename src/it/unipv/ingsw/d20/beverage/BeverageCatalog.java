package it.unipv.ingsw.d20.beverage;

import java.util.HashMap;
import java.util.Map;

public class BeverageCatalog {
	private Map<String, BeverageDescription> catalog;
	
	public BeverageCatalog() {
		catalog=new HashMap<String, BeverageDescription>();
	}
	
	public BeverageDescription getBeverageDesc(String code) { //DATO LA STRINGA RESTITUISCE L'OGGETTO CORRISPONDENTE NELLA MAPPA
		if(catalog.containsKey(code))	return catalog.get(code);
		return null;
	}
	
	public void addBeverageDescription(BeverageDescription b) {//AGGIUNGE UNA NUOVA BEVERAGE DESCRIPTION NELLA MAPPA
		catalog.put(b.getCode(),b);
	}
	
	public Map<String, BeverageDescription> getCatalog() { //METODO GET DI CATALOG
		return catalog;
	}

}