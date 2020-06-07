package it.unipv.ingsw.d20.persistence.BvCatalog;

import java.util.ArrayList;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;

public interface IBvCatalogDao {
		
	public ArrayList<BvCatalogPOJO> getBeverageCatalogByType(int type);
	
	public BeverageCatalog getBeverageCatalog();
	
}
