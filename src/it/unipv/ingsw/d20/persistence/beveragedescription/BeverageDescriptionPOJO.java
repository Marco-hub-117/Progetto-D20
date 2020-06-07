package it.unipv.ingsw.d20.persistence.beveragedescription;

public class BeverageDescriptionPOJO {
	
	private String bvName;
	private double price;
	private String idRecipe;
	
	public BeverageDescriptionPOJO(String bvName, double price,String idRecipe) {
		this.bvName = bvName;
		this.price = price;
		this.idRecipe = idRecipe;
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

	public String getIdRecipe() {
		return idRecipe;
	}

	public void setIdRecipe(String idRecipe) {
		this.idRecipe = idRecipe;
	}

	
}
