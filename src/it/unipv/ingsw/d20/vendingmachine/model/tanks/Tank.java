package it.unipv.ingsw.d20.vendingmachine.model.tanks;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;

/**
 * Rappresenta il serbatoio che contiene un ingrediente.
 * 
 */
public class Tank {
	
	private Ingredients ingredient;
	private double level; //il livello è in percentuale
	private double temperature;
	private double volume; //capacita nominale del serbatoio
	
	/**
	 * Costruttore della classe Tank, la temperatura è inizializzata a 20°C, la quantita' a 0
	 * @param ingredient ingrediente contenuto nel Tank
	 * @param volume capacità nominale del serbatoio
	 */
	public Tank(Ingredients ingredient, double volume) {
		this.ingredient = ingredient;
		this.level = 0; 
		this.temperature = 20; //si inizializza la temperatura a 20 gradi centigradi
		this.volume=volume;
	}
	
	/**
	 * Costruttore del Tank permette di specificare sia la temperatura sia il livello
	 * @param ingredient ingrediente contenuto nel Tank
	 * @param level livello di riempimento del Tank in percentuale
	 * @param temperature temperatura del Tank
	 * @param volume capacità nominale del serbatoio
	 */
	public Tank(Ingredients ingredient, double level, double temperature, double volume) {
		this.ingredient = ingredient;
		if(level > 100) 
			this.level = 100;
		else this.level = level;
		this.temperature = temperature; 
		this.volume = volume;
	}
	
	/**
	 * Abbassa il livello di riempimento del tank della quantità specificata.
	 * @param quantity quantità da diminuire
	 */
	public void lowerLevelBy(double quantity) {
		if (quantity>0) {
			level = level - quantity*100/volume;
		}
	}
	
	/**
	 * Riporta al massimo il livello di riempimento del tank.
	 * 
	 */
	public void refill() { 
		this.level = 100.0; 
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
	
	public double getVolume() {
		return volume;
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
