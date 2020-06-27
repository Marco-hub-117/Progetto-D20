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
	
	private final IVendingDao vendingMachine;
	private final ISaleDao sale;
	private final IBvCatalogDao beverageCatalog;
	private final IBeverageDescriptionDao beverageDescription;
	private final IIngredientRecipeDao ingredientRecipe;
	private final IKeyDao key;
	private final IOperatorDao operator;
	private final VendingLocalIO localMachine;
	
	private PersistenceFacade() {
		vendingMachine = new VendingRdbDao();
		sale = new SaleRdbDao();
		beverageCatalog = new BvCatalogRdbDao();
		beverageDescription = new BeverageDescriptionRdbDao();
		ingredientRecipe = new IngredientRecipeRdbDao();
		key = new KeyRdbDao();
		operator = new OperatorRdbDao();
		localMachine = new VendingLocalIO();
	}
	
	public static synchronized PersistenceFacade getInstance() {
		
		if(instance == null) {
			instance = new PersistenceFacade();
		}
		return instance;
	}

	public IVendingDao getVendingDao() {
		return vendingMachine;
	}
	
	public ISaleDao getSaleDao() {
		return sale;
	}
	
	public IBvCatalogDao getBvCatalogDao() {
		return beverageCatalog;
	}
	
	public IBeverageDescriptionDao getBeverageDescriptionDao() {
		return beverageDescription;
	}
	
	public IIngredientRecipeDao getIngredientRecipeDao() {
		return ingredientRecipe;
	}
	
	public VendingLocalIO getVendingLocalIO() {
		return localMachine;
	}

	public IKeyDao getKeyDao() {
		return key;
	}
	
	public IOperatorDao getOperatorDao() {
		return operator;
	}

}
