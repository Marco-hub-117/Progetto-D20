package it.unipv.ingsw.d20.DBMS;

import java.sql.SQLException;


public class VendingRdbDao implements IVendingDao{

	private RdbOperations op;
	
	
	public VendingRdbDao() {
		op = new RdbOperations();
	}
	
	public String getAddressById(String id) {
		
		
		return op.getAddressById(id);
	}

	@Override
	public void addVending(String Id, String Address) {
		// TODO Auto-generated method stub
		op.addVending(Id, Address);
	}

}
