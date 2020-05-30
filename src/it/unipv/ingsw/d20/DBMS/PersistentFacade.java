package it.unipv.ingsw.d20.DBMS;

public class PersistentFacade {
	
	
	
/**
 * Classe facade verso DBMS implementata con singleton 
 */
	
	private static PersistentFacade instance;
	
	private PersistentFacade() {}
	
	public static synchronized PersistentFacade getInstance() {
		
		if(instance == null) {
			instance = new PersistentFacade();
		}
		return instance;
	}

	public IVendingDao getVendingDao() {
		return new VendingRdbDao();
	}


}
