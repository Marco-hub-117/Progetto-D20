package it.unipv.ingsw.d20.util.persistence.key;

import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.RdbOperations;

public class KeyRdbDao implements IKeyDao{
	
	private RdbOperations op;

	@Override
	public ArrayList<KeyPOJO> getAllKeys() {
		return op.getAllKeys();
	}

	@Override
	public KeyPOJO getKey(String serialCode) {
		return op.getKey(serialCode);
	}

	@Override
	public void addKey(String serialCode, double credit) {
		KeyPOJO key= new KeyPOJO(serialCode, credit);
		op.addKey(key);
	}

	@Override
	public boolean updateCredit(String serialCode, double credit) {
		op.updateKeyCredit(serialCode, credit);
		return true;
	}

	@Override
	public double getCredit(String serialCode) {
		return op.getKeyCredit(serialCode);
	}

	@Override
	public void deactivateKey(String serialCode) {
		op.deactivateKey(serialCode);
	}

}
