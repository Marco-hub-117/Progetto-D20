package it.unipv.ingsw.d20.vendingmachine.model.beverage;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class BeverageDescription {

	private String code;
	private String name;
	private double price;
	private Map<Ingredients, Double> ingredients;  
	
	public BeverageDescription(String code,String name, double price) {
		this.code = code;
		this.name=name;
		this.price = price;
		this.ingredients = new HashMap<Ingredients, Double>();
	}
	
	public void addIngredient(Ingredients i) {//aggiunge un ingrediente e imposta la sua quantita a 0.0
		ingredients.put(i, 0.0);
	}
	public void addIngredient(Ingredients i, double q) {//aggiunge un ingrediente specificando la quantita
		ingredients.put(i, q);
	}
	public void changeQuantity(Ingredients i, double q){ //cambia la quantità di un ingrediente già presente
		ingredients.replace(i, q); //cambia la quantità solo se la chiave esiste
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
	
	public String toStringGui() {
		String description = "Codice " + code +": " + name +  " | Prezzo: €" + String.format("%.2f", price) + "\n\n";
		return description;
	}

}