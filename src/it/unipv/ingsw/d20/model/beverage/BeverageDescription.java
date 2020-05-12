package it.unipv.ingsw.d20.model.beverage;

import java.util.HashMap;
import java.util.Map;

public class BeverageDescription {

	String code;
	double price;
	private Map<Ingredient, Double> ingredients;  
	
	public BeverageDescription(String code,double price) {
		this.code=code;
		this.price=price;
		this.ingredients=new HashMap<Ingredient, Double>();
	}

	public void addIngredient(Ingredient i,double q) { //aggiunge un ingrediente e la sua quantità nella mappa
		ingredients.put(i, q);
	}
	public void setIngredientQuantity(Ingredient i, double q){ //cambia la quantità di un ingrediente già presente
		ingredients.replace(i, q); //cambia la quantità solo se la chiave esiste
	}

	public String getCode() {
		return code;
	}

	public Map<Ingredient, Double> getIngredients() { //resituisce la tutta la mappa degli ingredienti
		return ingredients;
	}

	public double getPrice() {
		return price;
	}
	public String toString() {
		String x="";
		String q="";
		for (Map.Entry<Ingredient, Double> entry : ingredients.entrySet()) {
		   q=q+"ingrediente: "+entry.getKey().getNome()+"|"+"quantitita: "+entry.getValue()+"\n";
	    }
		x="code: "+code+", price: "+price+", ingredienti:\n"+q+"\n";
		return x;
	}

}