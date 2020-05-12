package it.unipv.ingsw.d20.beverage;

public class Tank {
	
	private String id;
	private double level;
	private double temperature;
	//quando viene instanziato un serbatoio, è vuoto.
	//L'id verrà assegnto dalla macchina cui appartiene il serbatoio
	public Tank(String id) {
		this.id=id;
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
	
}
