package it.unipv.ingsw.d20.vendingmachine.model.beverage;

public class Tank {
	
	private String id;
	private double level; //
	private double temperature;
	
	//quando viene instanziato un serbatoio, è vuoto.
	//L'id verrà assegnto dalla macchina cui appartiene il serbatoio
	public Tank(String id) {
		this.id = id;
		this.level = 0;
		this.temperature = 20; //si inizializza la temperatura a 20 gradi centigradi
	}
	
	public String getId() {
		return id;
	}
	
	public double getLevel() {
		return level;
	}
	
	public double getTemperature() {
		return temperature;
	}
	
	public void refil() {
		//il livello del serbatoio � in percentuale
		this.level = 100.0;
	}
	
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	public String toString() {
		String tankInfo = "";
		tankInfo = "ID: " + id + " | Level: " + level + " | Temperature: " + temperature + "\n";
		return tankInfo;
	}
}
