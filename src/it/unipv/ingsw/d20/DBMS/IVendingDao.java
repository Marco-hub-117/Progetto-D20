package it.unipv.ingsw.d20.DBMS;

public interface IVendingDao {
	
	/*
	 * Questa interfaccia serve poiche potremmo voler implementare piu metodi di persistenza differenti
	 */
	
	public String getAddressById(String id);
	
	public void addVending(String Id, String Address);
	

}
