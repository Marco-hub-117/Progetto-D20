package it.unipv.ingsw.d20.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;
import it.unipv.ingsw.d20.vendingmachine.model.tanks.Tank;

public class VendingMachineInfo {
	
	private Date lastUpdate;
	private double currentAmount;
	private List<Tank> tankList = new ArrayList<>();
	private VendingMachineStatus status;
	
	public VendingMachineInfo() {
		currentAmount = 0;
		refreshLastUpdate();
	}
	
	public VendingMachineInfo(String cashInfo, String tankInfo, String statusInfo) {
		currentAmount = Double.parseDouble(cashInfo);
		setTankList(tankInfo);
		setStatusString(statusInfo);
		refreshLastUpdate();
	}
	
	public Date getLastUpdate() {
		return lastUpdate;
	}
	
	private void refreshLastUpdate() {
		lastUpdate = new Date();
	}
	
	public double getCurrentAmount() {
		return currentAmount;
	}
	
	public List<Tank> getTankList() {
		return tankList;
	}
	
	private void setTankList(String tankInfo) {
		String[] infoSplit = tankInfo.split(" ");

		for (int i = 0; i < infoSplit.length; i = i + 4) {
			Ingredients ingredient = Ingredients.valueOf(infoSplit[i]);
			double level = Double.parseDouble(infoSplit[i + 1]);
			double temperature = Double.parseDouble(infoSplit[i + 2]);
			double volume = Double.parseDouble(infoSplit[i + 3]);
			Tank t = new Tank(ingredient, level, temperature, volume);
			
			tankList.add(t);
		}
		
	}
	
	public VendingMachineStatus getStatus() {
		return status;
	}
	
	public String getStatusString() {
		return status.toString();
	}
	
	public void setStatus(VendingMachineStatus status) {
		this.status=status;	
	}
	
	public void setStatusString(String statusInfo) {
		status=VendingMachineStatus.valueOf(statusInfo);
	}

}












