package it.unipv.ingsw.d20.model.vendingmachine;

public interface IVendingMachineManage {
	
	
	
	public void getTanksLevels();
	
	public void setTankLevel(String id, Double level);
		
	public void withdrawAmount();

	public void setTankSettings(String id, Double temp);

	public void setIngredient(String code, String name, Double quantity);

	

}
