package it.unipv.ingsw.d20.util.persistence.vending;

import java.util.ArrayList;

/**
 * Interfaccia per accedere a un VendingDao. E' possibile utilizzarla per ottenere le informazioni necessarie.
 *
 */
public interface IVendingDao {
	
	/**
	 * Aggiunge una VendingMachine al database.
	 * @param vending POJO della vending machine
	 */
	public void addVending(VendingPOJO vending);
	
	public ArrayList<VendingPOJO> getAllVendings();
	
	public VendingPOJO getVending(String idVending);	
	
	/*
	 * Restituisce la posizione della vending machine 
	 * @param id della vending
	 * @return posizione della vending con l'id passato come parametro.
	 */
	//public VendingMachineStatus getVendingLocationById(String id);

}
