package it.unipv.ingsw.d20.util.persistence.key;

import java.util.ArrayList;


public interface IKeyDao {
	
	public ArrayList <KeyPOJO> getAllKeys();
	
	public KeyPOJO getKey(String serialCode);
	
	public void addKey(String serialCode, double credit);
	
	public boolean updateCredit(String serialCode, double credit);

	public double getCredit(String serialCode);
	
	public void deactivateKey(String serialCode);

}
