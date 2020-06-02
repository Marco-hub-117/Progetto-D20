package it.unipv.ingsw.d20.persistence.BvCatalog;

public class BvCatalogPOJO {
	
	private String idBev;
	private double price;
	private String idBevDesc;
	private int type;
	
	public BvCatalogPOJO(String idBev, double price, String idBevDesc, int type) {
		this.idBev = idBev;
		this.price = price;
		this.idBevDesc = idBevDesc;
		this.type = type;
	}

	public String getIdBev() {
		return idBev;
	}

	public void setIdBev(String idBev) {
		this.idBev = idBev;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getIdBevDesc() {
		return idBevDesc;
	}

	public void setIdBevDesc(String idBevDesc) {
		this.idBevDesc = idBevDesc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
