package it.unipv.ingsw.d20.DBMS;

import java.sql.SQLException;

/**
 * Implementazione dell'interfaccia IVendingDao. Implementa il DAO relativo al database relazionale.
 *
 */
public class VendingRdbDao implements IVendingDao{

	private RdbOperations op;
	
	
	public VendingRdbDao() {
		op = new RdbOperations();
	}
	
	public String getAddressById(String id) {
		return op.getVendingAddressById(id);
	}

	@Override
	public void addVending(String Id, String Address) {
		op.addVending(Id, Address);
	}

}
