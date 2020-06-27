package it.unipv.ingsw.d20.util.persistence.paymentKey;

import java.util.ArrayList;


public interface IKeyDao {
	
	public ArrayList <KeyPOJO> getAllKeys();
	
	public KeyPOJO getKey(String serialCode);
	
	public void addKey(int serialCode, double credit);
	
	public boolean updateCredit(String serialCode, double credit);

	public double getCredit(String serialCode);
	
	public void deactivateKey(String serialCode);

}
