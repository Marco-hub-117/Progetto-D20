package it.unipv.ingsw.d20.util.persistence.beveragedescription;

import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.RdbOperations;
import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.IngredientRecipePOJO;

/**
 * Implementazione dell'interfaccia IBeverageDescriptionDao. Prende i dati dal DB.
 * 
 *
 */
public class BeverageDescriptionRdbDao implements IBeverageDescriptionDao {
	
	private RdbOperations op;
	
	public BeverageDescriptionRdbDao(RdbOperations op) {
		this.op = op;
	}

	@Override
	public double getPriceByBevName(String bevName) {
		return op.getPriceByBevName(bevName);
	}

	@Override
	public void addBeverageDescription(BeverageDescriptionPOJO bv) {
		op.addBeverageDescription(bv);
	}

	@Override
	public void addBeverageDescription(BeverageDescriptionPOJO bv, ArrayList<IngredientRecipePOJO> ingr) {
		op.addBeverageDescription(bv, ingr);
	}

	@Override
	public BeverageDescriptionPOJO getBeverageDescriptionByBevName(String BevName) {
		return op.getBeverageDescriptionByBevName(BevName);
	}

	@Override
	public ArrayList<BeverageDescriptionPOJO> getAllBeverageDescriptions() {
		return op.getAllBeverageDescriptions();
	}
	
	

}
