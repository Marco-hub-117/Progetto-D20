package it.unipv.ingsw.d20.persistence;

import it.unipv.ingsw.d20.persistence.BeverageDescription.*;
import it.unipv.ingsw.d20.persistence.BvCatalog.*;
import it.unipv.ingsw.d20.persistence.LocalIOHandler.VendingLocalIO;
import it.unipv.ingsw.d20.persistence.ingredientRecipe.*;
import it.unipv.ingsw.d20.persistence.key.IKeyDao;
import it.unipv.ingsw.d20.persistence.sale.*;
import it.unipv.ingsw.d20.persistence.vending.*;

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
		// TODO Auto-generated method stub
		return null;
	}
	

}
