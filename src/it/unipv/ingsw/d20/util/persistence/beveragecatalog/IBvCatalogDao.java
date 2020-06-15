package it.unipv.ingsw.d20.util.persistence.beveragecatalog;

import java.util.ArrayList;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;

public interface IBvCatalogDao {
		
	public ArrayList<BvCatalogPOJO> getBeverageCatalogByType(int type);
	
	public BeverageCatalog getBeverageCatalog(int type);
	
}
