package it.unipv.ingsw.d20.persistence.BvCatalog;

import java.util.ArrayList;

import it.unipv.ingsw.d20.persistence.RdbOperations;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;

public class BvCatalogRdbDao implements IBvCatalogDao {
	
	private RdbOperations op;
	
	public BvCatalogRdbDao() {
		op = new RdbOperations();
	}

	@Override
	public ArrayList<BvCatalogPOJO> getBeverageCatalogByType(int type) {
		return op.getBeverageCatalogByType(type);
	}
	
	public BeverageCatalog getBeverageCatalog() {
		return null;
	}

}
