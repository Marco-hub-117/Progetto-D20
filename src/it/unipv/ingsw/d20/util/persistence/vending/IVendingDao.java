package it.unipv.ingsw.d20.util.persistence.vending;

import java.util.ArrayList;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;

/**
 * Interfaccia per Accedere a un VendingDao. � possibile utilizzarla per ottenere le informazioni necessarie.
 *
 */
public interface IVendingDao {
	
	/*
	 * Questa interfaccia serve poiche potremmo voler implementare diversi metodi per accedere alla persistenza. 
	 * Per esempio attraverso un database o un file di testo.
	 */
	
	/**
	 * Restituisce l'indirizzo della vending machine 
	 * @param id della vending
	 * @return indirizzo della vending con l'id passato come parametro.
	 */
	public VendingMachineStatus getVendingStatusById(String id);
	
	/**
	 * Aggiunge una VendingMachine al database
	 * @param Id id della vending machine.
	 * @param Address indirizzo, riferito alla posizione della vending machine.
	 */
	public void addVending(VendingPOJO vending);
	
	public ArrayList<VendingPOJO> getAllVendings();
	
	public VendingPOJO getVending(String idVending);
	
	public void updateVendingStatus(String idVending,VendingMachineStatus newStatus);
	
	public void updateVendingAmount(String idVending,double amount);
	
	public void updateVendingTankLevel(String idVending,String tankLevel);
	
	// public void updateVendingTankTemp(String idVending,String TankTemp); DA VEDERE UTILITA'
	
	
	

}
