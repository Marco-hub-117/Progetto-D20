package it.unipv.ingsw.d20.persistence.vending;

import java.util.ArrayList;

/**
 * Interfaccia per Accedere a un VendingDao. é possibile utilizzarla per ottenere le informazioni necessarie.
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
	public String getAddressById(String id);
	
	/**
	 * Aggiunge una VendingMachine al database
	 * @param Id id della vending machine.
	 * @param Address indirizzo, riferito alla posizione della vending machine.
	 */
	public void addVending(VendingPOJO vending);
	
	public ArrayList<VendingPOJO> getAllVending();
	

}
