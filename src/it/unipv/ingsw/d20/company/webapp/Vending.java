package it.unipv.ingsw.d20.company.webapp;

public class Vending {
	private int id;
	private String location;
	private String status;
	private String type;
	
	public Vending(int id, String location, String status, String type) {
		super();
		this.id = id;
		this.location = location;
		this.status = status;
		this.type=type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
