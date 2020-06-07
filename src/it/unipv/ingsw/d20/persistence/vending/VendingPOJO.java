package it.unipv.ingsw.d20.persistence.vending;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;

/**
 * Classe che serve come "contenitore" di informazioni di una table Vending del database.
 * 
 */
public class VendingPOJO {
	
	private String idVending;
	private VendingMachineStatus status;
	
	public VendingPOJO(String idVending, VendingMachineStatus status) {
		this.idVending = idVending;
		this.status = status;
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
	
	
	
}
