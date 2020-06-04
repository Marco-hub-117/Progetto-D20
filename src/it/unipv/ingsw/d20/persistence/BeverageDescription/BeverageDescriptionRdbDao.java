package it.unipv.ingsw.d20.persistence.BeverageDescription;

import java.util.ArrayList;

import it.unipv.ingsw.d20.persistence.RdbOperations;
import it.unipv.ingsw.d20.persistence.ingredientRecipe.IngredientRecipePOJO;

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
	
	

}
