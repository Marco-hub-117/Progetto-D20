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
	//ATTENZIONE: serve ingredient? si riduce ad una stringa...
	public void addIngredient(Ingredient i,double q) {
		ingredients.put(i, q);
	}
	public void setIngredientQuantity(Ingredient i,double q){
		ingredients.replace(i, q);
	}

	public String getCode() {
		return code;
	}

	public Map<Ingredient, Double> getIngredients() {
		return ingredients;
	}

	public double getPrice() {
		return 0;
	}

}