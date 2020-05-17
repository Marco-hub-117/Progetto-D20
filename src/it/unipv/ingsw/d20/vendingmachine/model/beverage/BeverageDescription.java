package it.unipv.ingsw.d20.vendingmachine.model.beverage;

import java.util.HashMap;
import java.util.Map;

public class BeverageDescription {

	String code;
	double price;
	private Map<Ingredients, Double> ingredients;  
	
	public BeverageDescription(String code, double price) {
		this.code = code;
		this.price = price;
		this.ingredients = new HashMap<Ingredients, Double>();
	}

	public void addIngredient(Ingredients i, double q) { //aggiunge un ingrediente e la sua quantità nella mappa
		ingredients.put(i, q);
	}
	
	public void setIngredientQuantity(Ingredients i, double q){ //cambia la quantità di un ingrediente già presente
		ingredients.replace(i, q); //cambia la quantità solo se la chiave esiste
	}

	public String getCode() {
		return code;
	}

	public Map<Ingredients, Double> getIngredients() { //resituisce la tutta la mappa degli ingredienti
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
		
		/* FOR ALTERNATIVO
		 * for (Ingredient i : ingredients.keySet()) {
		 *     recipe = recipe + "Ingredient: " + i.getName() + "| Quantity: " + ingredients.get(i) + "\n";
		 * }
		 */
		
		description = "Code: " + code + " | Price: " + price + " | Ingredients:\n" + ingredientList + "\n";
		return description;
	}

}







