package it.unipv.ingsw.d20.persistence.BvCatalog;

import java.util.ArrayList;

public interface IBvCatalogDao {
		
	public ArrayList<BvCatalogPOJO> getBeverageCatalogByType(int type);
	
}
