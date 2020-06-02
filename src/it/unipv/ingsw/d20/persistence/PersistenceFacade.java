package it.unipv.ingsw.d20.persistence;

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
	
	


}
