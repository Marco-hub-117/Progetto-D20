package it.unipv.ingsw.d20.vendingmachine.model.beverage;
//hhh
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
	//Non mi sembra una buona pratica quella di aggiungere al momento dell'instanziazione di 
	//BeverageDescription una lista di ingredienti, per evitare di scrivere valori specifici 
	//direttamente nel codice. 
	//In ogni caso con la modifica del metodo addInredient, il valore di ciascun ingrediente 
	//viene settato a zero.
	public void addIngredient(Ingredients i) {
		//aggiunge un ingrediente e setta la sua quantit� a 0.0
		ingredients.put(i, 0.0);
	}
	public void setIngredientQuantity(Ingredients i, double q){ //cambia la quantità di un ingrediente già presente
		ingredients.replace(i, q); //cambia la quantità solo se la chiave esiste
	}
	public String getName() {
		return name;
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







