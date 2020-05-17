package it.unipv.ingsw.d20.vendingmachine.model;

public interface IVendingMachineRemote {
	
	public void getTanksLevels();
			
	public void setTankSettings(String id, Double temp);

	public void setIngredient(String code, String name, Double quantity);

}
