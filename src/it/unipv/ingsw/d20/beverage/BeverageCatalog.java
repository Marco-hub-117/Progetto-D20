package it.unipv.ingsw.d20.beverage;

import java.util.HashMap;
import java.util.Map;

public class BeverageCatalog {
	private Map<String, BeverageDescription> catalog;
	
	public BeverageCatalog() {
		catalog=new HashMap<String, BeverageDescription>();
	}
	
	public BeverageDescription getBeverageDesc(String code) {
		//da fare
		//commento di prova
		return null;
	}
	
	public void addBeverageDescription(BeverageDescription b) {
		//catalog.put(, )
	}
	public Map<String, BeverageDescription> getCatalog() {
		return catalog;
	}
}
