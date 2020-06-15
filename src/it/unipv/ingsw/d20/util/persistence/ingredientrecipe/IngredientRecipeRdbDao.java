package it.unipv.ingsw.d20.util.persistence.ingredientrecipe;

import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.RdbOperations;

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

	@Override
	public ArrayList<IngredientRecipePOJO> getAllIngredientRecipeByIdRecipe(String idRecipe) {
		// TODO Auto-generated method stub
		return op.getAllIngredientRecipeByIdRecipe(idRecipe);
	}
	
	

}
