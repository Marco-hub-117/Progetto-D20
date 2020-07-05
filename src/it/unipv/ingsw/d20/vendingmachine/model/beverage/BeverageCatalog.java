package it.unipv.ingsw.d20.vendingmachine.model.beverage;

import java.util.HashMap;
import java.util.Map;

/**
 * La classe rappresenta il catalogo delle bevande, ad ogni bevanda è associata la sua descrizione.
 * 
 */
public class BeverageCatalog {
	
	private Map<String, BeverageDescription> catalog;
	/**
	 * Costruttore della classe BeverageCatalog
	 * 
	 */
	public BeverageCatalog() {
		catalog = new HashMap<String, BeverageDescription>();
	}
	
	/**
	 * Restituisce la BeverageDescription della bevanda dato il suo codice.
	 * @param code codice della bevanda
	 * @return catalog catalogo delle bevande
	 */
	public BeverageDescription getBeverageDesc(String code) {
		if(catalog.containsKey(code)) {	
			return catalog.get(code);
		} else {
			return null;
		}
	}
	
	/**
	 * Dato il codice di una bevanda ne imposta un ingrediente e la relativa quantit�.
	 * @param code codice della bevanda
	 * @param i ingrediente
	 * @param q quantita'
	 */
	public void setIngredient(String code, Ingredients i, Double q) {
		if(catalog.containsKey(code)) {
			catalog.get(code).changeQuantity(i, q);
		}
	}
	
	/**
	 * Aggiunge una nuova BeverageDescription.
	 * @param b bevanda da aggiungere
	 */
	public void addBeverageDescription(BeverageDescription b) {
		catalog.put(b.getCode(), b);
	}
	
	public Map<String, BeverageDescription> getCatalog() {
		return catalog;
	}

	public String toString() {
		String beverageList = "";

		for (BeverageDescription bvDesc : catalog.values()) {
			beverageList = beverageList + bvDesc.toString();
		}

		return beverageList;
	}
	
	/**
	 * Un metodo toString per la visualizzazione nella GUI
	 * @return beverageList lista delle bevande
	 */
	public String toStringGui() {
		String beverageList = "";		
		
		for (BeverageDescription bvDesc : catalog.values()) {
			beverageList = beverageList  + bvDesc.toStringGui();	
		}
		
		return beverageList;
	}
	
	/**
	 * Restituisce il nome della bevanda dato il codice
	 * @param code codice della bevanda
	 * @return nome della bevanda
	 */
	public String getBevName(String code) {
		return catalog.get(code).getName();
	}
	
	/**
	 * Restituisce la quantità dell'ingrediente dato il codice della bevanda e l'ingrediente richiesto
	 * @param code codice della bevanda
	 * @ingr ingr ingrediente di cui si vuole conoscere la quantità
	 * @return quantità dell'ingrediente
	 */
	public Double getIngrQuant(String code, Ingredients ingr) {
		return getBeverageDesc(code).getIngredientQuant(ingr);
	}
	
}