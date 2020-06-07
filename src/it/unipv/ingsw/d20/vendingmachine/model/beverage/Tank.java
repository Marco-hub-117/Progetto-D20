package it.unipv.ingsw.d20.vendingmachine.model.beverage;

public class Tank {
	
	private Ingredients ingredient;
	private double level; //
	private double temperature;
	
	//quando viene instanziato un serbatoio, è vuoto.
	//L'id verrà assegnto dalla macchina cui appartiene il serbatoio
	public Tank(Ingredients ingredient) {
		this.ingredient = ingredient;
		this.level = 0;
		this.temperature = 20; //si inizializza la temperatura a 20 gradi centigradi
	}
	
	public Tank(Ingredients ingredient,double level,double temperature) {
		this.ingredient = ingredient;
		this.level = level;
		this.temperature = temperature; 
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
	
	public void refill() {
		//il livello del serbatoio � in percentuale
		this.level = 100.0;
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
