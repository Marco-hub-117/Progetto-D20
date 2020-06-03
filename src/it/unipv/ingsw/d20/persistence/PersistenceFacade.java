package it.unipv.ingsw.d20.persistence;

import it.unipv.ingsw.d20.persistence.BeverageDescription.BeverageDescriptionRdbDao;
import it.unipv.ingsw.d20.persistence.BeverageDescription.IBeverageDescriptionDao;
import it.unipv.ingsw.d20.persistence.BvCatalog.BvCatalogRdbDao;
import it.unipv.ingsw.d20.persistence.BvCatalog.IBvCatalogDao;
import it.unipv.ingsw.d20.persistence.sale.ISaleDao;
import it.unipv.ingsw.d20.persistence.sale.SaleRdbDao;
import it.unipv.ingsw.d20.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.persistence.vending.VendingRdbDao;

/**
 * Classe facade verso DBMS implementata con singleton. 
 */
public class PersistenceFacade {
	
	private static PersistenceFacade instance;
	
	private PersistenceFacade() {}
	
	public static synchronized PersistenceFacade getInstance() {
		
		if(instance == null) {
			instance = new PersistenceFacade();
		}
		return instance;
	}

	public IVendingDao getVendingDao() {
		return new VendingRdbDao();
	}
	
	public ISaleDao getSaleDao() {
		return new SaleRdbDao();
	}
	
	public IBvCatalogDao getBvCatalogDao() {
		return new BvCatalogRdbDao();
	}
	
	public IBeverageDescriptionDao getBeverageDescriptionDao() {
		return new BeverageDescriptionRdbDao();
	}
	
	


}
