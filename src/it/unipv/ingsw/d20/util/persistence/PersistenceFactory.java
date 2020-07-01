package it.unipv.ingsw.d20.util.persistence;

import java.sql.Connection;

import it.unipv.ingsw.d20.util.persistence.beveragecatalog.*;
import it.unipv.ingsw.d20.util.persistence.beveragedescription.*;
import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.*;
import it.unipv.ingsw.d20.util.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.util.persistence.operator.IOperatorDao;
import it.unipv.ingsw.d20.util.persistence.operator.OperatorRdbDao;
import it.unipv.ingsw.d20.util.persistence.paymentKey.IKeyDao;
import it.unipv.ingsw.d20.util.persistence.paymentKey.KeyRdbDao;
import it.unipv.ingsw.d20.util.persistence.sale.*;
import it.unipv.ingsw.d20.util.persistence.vending.*;

/**
 * Classe utile per ottenere tutti le possibili istanze di classi che implementano metodi per la persistenza dei dati. 
 * Con questa classe e' possibile ottenere tutti i DAO.
 * Implementata come un singleton, utilizzando il pattern "Factory".
 */
public class PersistenceFactory {
	
	private static PersistenceFactory instance;
	
	private final RdbOperations operations;
	private final IVendingDao vendingMachine;
	private final ISaleDao sale;
	private final IBvCatalogDao beverageCatalog;
	private final IBeverageDescriptionDao beverageDescription;
	private final IIngredientRecipeDao ingredientRecipe;
	private final IKeyDao key;
	private final IOperatorDao operator;
	private final VendingLocalIO localMachine;
	
	private PersistenceFactory() {
		operations=new RdbOperations();
		vendingMachine = new VendingRdbDao(operations);
		sale = new SaleRdbDao(operations);
		beverageCatalog = new BvCatalogRdbDao(operations);
		beverageDescription = new BeverageDescriptionRdbDao(operations);
		ingredientRecipe = new IngredientRecipeRdbDao(operations);
		key = new KeyRdbDao(operations);
		operator = new OperatorRdbDao(operations);
		localMachine = new VendingLocalIO();
	}
	
	public static synchronized PersistenceFactory getInstance() {
		
		if(instance == null) {
			instance = new PersistenceFactory();
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

	public RdbOperations getOperations() {
		return operations;
	}
	/**
	 * Metodo che testa la connessione al database
	 * */
	public boolean testConnection() {
		Connection connection=null;
		if (operations.isOpen(operations.startConnection(connection))) {
			return true;
		}
		return false;
	}
}
