package it.unipv.ingsw.d20.vendingmachine.model.beverage;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.exceptions.DeliveryFailedException;

public class Tank {
	
	private Ingredients ingredient;
	private double level; //il livello è in percentuale
	private double temperature;
	
	public Tank(Ingredients ingredient) {
		this.ingredient = ingredient;
		this.level = 0; 
		this.temperature = 20; //si inizializza la temperatura a 20 gradi centigradi
	}
	
	public Tank(Ingredients ingredient, double level, double temperature) {
		this.ingredient = ingredient;
		this.level = level;
		this.temperature = temperature; 
	}
	
	public void lowerLevelBy(double quantity) throws DeliveryFailedException { //imposta il huovo livello del tank dopo l'erogazione di una bevanda
		if (quantity > level) {
			throw new DeliveryFailedException();
		}
		level -= quantity;
	}
	
	public void refill() { //riporta al massimo il livello del tank
		this.level = 100.0; //il livello del serbatoio e' in percentuale
	}
	
	public Ingredients getId() {
		return ingredient;
	}
	
	public double getLevel() {
		return level;
	}
	
	public double getTemperature() {
		return temperature;
	}
	
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	public String toString() {
		String tankInfo = "";
		tankInfo = "Ingredient: " + String.valueOf(ingredient) + " | Level: " + level + " | Temperature: " + temperature + "\n";
		return tankInfo;
	}
}
