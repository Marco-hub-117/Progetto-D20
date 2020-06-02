package it.unipv.ingsw.d20.persistence.vending;

/**
 * Classe che serve come "contenitore" di informazioni di una table Vending del database.
 * 
 */
public class VendingPOJO {
	
	private String idVending, address;
	
	public VendingPOJO(String idVending, String address) {
		this.idVending = idVending;
		this.address = address;
	}

	public String getIdVending() {
		return idVending;
	}

	public void setIdVending(String idVending) {
		this.idVending = idVending;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
