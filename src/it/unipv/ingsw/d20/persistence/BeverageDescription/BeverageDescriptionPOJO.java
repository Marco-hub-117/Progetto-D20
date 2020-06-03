package it.unipv.ingsw.d20.persistence.BeverageDescription;

public class BeverageDescriptionPOJO {
	
	private String bvName;
	private double price;
	
	public BeverageDescriptionPOJO(String bvName, double price) {
		this.bvName = bvName;
		this.price = price;
	}

	public String getBvName() {
		return bvName;
	}

	public void setBvName(String bvName) {
		this.bvName = bvName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
