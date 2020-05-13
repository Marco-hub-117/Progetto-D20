package it.unipv.ingsw.d20.model.vendingmachine;

import java.util.List;
import java.util.Map;

import it.unipv.ingsw.d20.model.beverage.Tank;

public class VendingMachine {

	private String id;
	private VendingMachineStatus status;
	private double totalAmount;
	private List Sale;
	private Double currentAmount;
	private String currentCode;
	private Map<String,Tank> tankList;

	public VendingMachine(String id) {
		this.id = id;
		this.setStatus(status.PRONTO);
			
		
	}
	
	public void insertAmount(Double amount) {
		
	}
	
	public void insertCode(String code) {
		
	}
	
	public void startTransaction() {
		
	}

	public void getTanksLevels() {
		
	}

	public void setTankLevel(String id, Double level) {
		
	}

	public void withdrawAmount() {

		
		
		
	}

	public void sendInfo() {
		
	}

	public void setTankSettings(String id, Double temp) {
		
	}

	public void setIngredient(String code, String name, Double quantity) {
		
	}

	
	public void setStatus(VendingMachineStatus status) {
		this.status = status;
	}
	
	

}
