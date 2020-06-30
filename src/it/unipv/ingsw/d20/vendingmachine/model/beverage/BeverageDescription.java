package it.unipv.ingsw.d20.vendingmachine.model.beverage;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe che rappresenta la descrizione di una bevanda.
 * 
 */
public class BeverageDescription {

	private String code;
	private String name;
	private double price;
	private Map<Ingredients, Double> ingredients;  
	/**
	 * Costruttore della classe BeverageDescription
	 * @param code codice della bevanda
	 * @param name nome della bevanda
	 * @param price costo della bevanda
	 * 
	 */
	public BeverageDescription(String code,String name, double price) {
		this.code = code;
		this.name=name;
		this.price = price;
		this.ingredients = new HashMap<Ingredients, Double>();
	}
	
	/**
	 * Aggiunge un ingrediente alla bevanda con la quantita' pari a 0.
	 * @param i Ingrediente da aggiungere
	 */
	public void addIngredient(Ingredients i) {
		ingredients.put(i, 0.0);
	}
	
	/**
	 * Aggiunge un ingrediente alla bevanda specificando la quantita'.
	 * @param i Ingrediente da aggiungere
	 * @param q Quantita' 
	 */
	public void addIngredient(Ingredients i, double q) {
		ingredients.put(i, q);
	}
	
	/**
	 * Cambia la quantita' di un ingrediente gia' presente.
	 * @param i Ingrediente da cambiare
	 * @param q Quantita' nuova
	 */
	public void changeQuantity(Ingredients i, double q){ 
		ingredients.replace(i, q);
	}
	
	public String getName() {
		return name;
	}
	
	public String getCode() {
		return code;
	}

	public Map<Ingredients, Double> getIngredients() {
		return ingredients;
	}

	public double getPrice() {
		return price;
	}
	
	public String toString() {
		String description = "";
		String ingredientList = "";
		
		for (Map.Entry<Ingredients, Double> entry : ingredients.entrySet()) {
		   ingredientList = ingredientList + "Ingredient: " + entry.getKey() + " | Quantity: " + entry.getValue() + "\n";
	    }
		
		description = "Code: " + code +" | Nome: " + name +  " | Price: " + price + " | Ingredients:\n" + ingredientList + "\n";
		return description;
	}
	
	/**
	 * Un metodo toString per la visualizzazione nella GUI
	 * @return description descrizione della bevanda
	 */
	public String toStringGui() {
		String description;
		if (name.length() > 10)
			description = "Cod. " + code +"		" + name +  "	€" + String.format("%.2f", price) + "\n\n";
		else 
			description = "Cod. " + code +"		" + name +  "		€" + String.format("%.2f", price) + "\n\n";
		return description;
	}

}





