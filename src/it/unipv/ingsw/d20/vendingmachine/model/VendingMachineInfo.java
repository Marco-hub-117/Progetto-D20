package it.unipv.ingsw.d20.vendingmachine.model;

import java.util.HashMap;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.Tank;

public class VendingMachineInfo {
	
	private String id;
	private VendingMachineStatus status;
	private double totalAmount;
	private HashMap<String,Tank> tankList;
	// mettere anche il catalogo delle bevande?
	
	
	public VendingMachineInfo(String id, VendingMachineStatus status, double totalAmount,HashMap<String, Tank> tankList) {
		this.id = id;
		this.status = status;
		this.totalAmount = totalAmount;
		this.tankList = tankList;
	}


	public String getId() {
		return id;
	}


	public VendingMachineStatus getStatus() {
		return status;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public HashMap<String, Tank> getTankList() {
		return tankList;
	}
	
	
	
	
	
	
	
	

}
