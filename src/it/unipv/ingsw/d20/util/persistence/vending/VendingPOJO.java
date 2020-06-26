package it.unipv.ingsw.d20.util.persistence.vending;

/**
 * Classe che serve come "contenitore" di informazioni di una table Vending del database.
 * 
 */
public class VendingPOJO {
	
	private String id;
	private String location; 
	private String type;  // tipo della macchinetta
	

	public VendingPOJO(String idVending, String location, String type) {
		this.id = idVending;
		this.location = location;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setIdVending(String idVending) {
		this.id = idVending;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "VendingPOJO [idVending=" + id + ", status=" + ", location=" + location + ", type="
				+ type + "]";
	}
	
}
