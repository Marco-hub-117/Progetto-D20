package it.unipv.ingsw.d20.persistence.beveragedescription;

import java.util.ArrayList;

import it.unipv.ingsw.d20.persistence.RdbOperations;
import it.unipv.ingsw.d20.persistence.ingredientrecipe.IngredientRecipePOJO;

public class BeverageDescriptionRdbDao implements IBeverageDescriptionDao {
	
	private RdbOperations op;
	
	public BeverageDescriptionRdbDao() {
		op = new RdbOperations();
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
		// TODO Auto-generated method stub
		return op.getBeverageDescriptionByBevName(BevName);
	}
	
	

}
