package it.unipv.ingsw.d20.util.persistence.ingredientrecipe;

import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.RdbOperations;

/**
 * Implementazione dell'interfaccia IIngredientRecipeDao. prende i dati dal DB.
 *
 *
 */
public class IngredientRecipeRdbDao implements IIngredientRecipeDao{

	private RdbOperations op;
	
	public IngredientRecipeRdbDao(RdbOperations op) {
		this.op = op;
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
		return op.getAllIngredientRecipeByIdRecipe(idRecipe);
	}

	@Override
	public boolean updateIngredientRecipe(String idRecipe, String ingredientName, double quantity) {
		op.updateIngredientRecipe(idRecipe, ingredientName, quantity);
		return true;
	}
	

}
