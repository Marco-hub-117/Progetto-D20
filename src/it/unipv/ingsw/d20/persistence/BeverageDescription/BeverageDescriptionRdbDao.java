package it.unipv.ingsw.d20.persistence.BeverageDescription;

import it.unipv.ingsw.d20.persistence.RdbOperations;

public class BeverageDescriptionRdbDao implements IBeverageDescriptionDao {
	
	private RdbOperations op;
	
	public BeverageDescriptionRdbDao() {
		op = new RdbOperations();
	}

	@Override
	public double getPriceByBevName(String bevName) {
		return op.getPriceByBevName(bevName);
	}
	
	

}
