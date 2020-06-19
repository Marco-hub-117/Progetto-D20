package it.unipv.ingsw.d20.util.persistence.vending;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;

/**
 * Classe che serve come "contenitore" di informazioni di una table Vending del database.
 * 
 */
public class VendingPOJO {
	
	private String idVending;
	private VendingMachineStatus status;
	private String location; 
	private String type;  // tipo della macchinetta
	private double amount;
	private String tankLevel; // Riportate nel formato level-level-level...
	private String tankTemp; //   Riportate nel formato temp-temp-temp...
	
	

	public VendingPOJO(String idVending, VendingMachineStatus status, String location, String type, double amount,
			String tankLevel, String tankTemp) {
		this.idVending = idVending;
		this.status = status;
		this.location = location;
		this.type = type;
		this.amount = amount;
		this.tankLevel = tankLevel;
		this.tankTemp = tankTemp;
	}

	public String getIdVending() {
		return idVending;
	}

	public void setIdVending(String idVending) {
		this.idVending = idVending;
	}

	public VendingMachineStatus getStatus() {
		return status;
	}
	
	public String getStringStatus() {
		return status.toString();	
	}

	public void setAddress(VendingMachineStatus status) {
		this.status = status;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTankLevel() {
		return tankLevel;
	}

	public void setTankLevel(String tankLevel) {
		this.tankLevel = tankLevel;
	}

	public String getTankTemp() {
		return tankTemp;
	}

	public void setTankTemp(String tankTemp) {
		this.tankTemp = tankTemp;
	}

	public void setStatus(VendingMachineStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "VendingPOJO [idVending=" + idVending + ", status=" + status + ", location=" + location + ", type="
				+ type + ", amount=" + amount + ", tankLevel=" + tankLevel + ", tankTemp=" + tankTemp + "]";
	}

		
	
	
}
