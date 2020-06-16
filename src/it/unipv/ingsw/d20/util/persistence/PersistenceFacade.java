package it.unipv.ingsw.d20.util.persistence;

import it.unipv.ingsw.d20.util.persistence.beveragecatalog.*;
import it.unipv.ingsw.d20.util.persistence.beveragedescription.*;
import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.*;
import it.unipv.ingsw.d20.util.persistence.key.IKeyDao;
import it.unipv.ingsw.d20.util.persistence.key.KeyRdbDao;
import it.unipv.ingsw.d20.util.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.util.persistence.operator.IOperatorDao;
import it.unipv.ingsw.d20.util.persistence.operator.OperatorRdbDao;
import it.unipv.ingsw.d20.util.persistence.sale.*;
import it.unipv.ingsw.d20.util.persistence.vending.*;

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
	
	public IingredientRecipeDao getIngredientRecipeDao() {
		return new IngredientRecipeRdbDao();
	}
	
	public VendingLocalIO getVendingLocalIO() {
		return new VendingLocalIO();
	}

	public IKeyDao getKeyDao() {
		return new KeyRdbDao();
	}
	
	public IOperatorDao getOperatorDao() {
		return new OperatorRdbDao();
	}
	

}
