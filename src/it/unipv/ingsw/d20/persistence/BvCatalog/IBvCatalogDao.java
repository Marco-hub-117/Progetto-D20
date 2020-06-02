package it.unipv.ingsw.d20.persistence.BvCatalog;

import java.util.ArrayList;

public interface IBvCatalogDao {
	
	public BvCatalogPOJO getBeverage(String idBeverage);
	
	public ArrayList<BvCatalogPOJO> getBeverageCatalogByType(int type);
	
}
