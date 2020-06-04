package it.unipv.ingsw.d20.persistence.ingredientRecipe;

import java.util.ArrayList;

import it.unipv.ingsw.d20.persistence.RdbOperations;

public class IngredientRecipeRdbDao implements IingredientRecipeDao{

	private RdbOperations op;
	
	public IngredientRecipeRdbDao() {
		op = new RdbOperations();
	}
	
	@Override
	public void addIngredientRecipe(ArrayList<IngredientRecipePOJO> ingr) {
		op.addIngredientRecipe(ingr);
	}

	@Override
	public void addIngredientRecipe(IngredientRecipePOJO ingr) {
		op.addIngredientRecipe(ingr);
	}
	
	

}
