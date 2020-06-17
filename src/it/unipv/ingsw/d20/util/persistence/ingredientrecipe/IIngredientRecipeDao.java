package it.unipv.ingsw.d20.util.persistence.ingredientrecipe;

import java.util.ArrayList;

public interface IIngredientRecipeDao {
	
	public void addIngredientRecipe(ArrayList<IngredientRecipePOJO> ingr);
	
	public void addIngredientRecipe(IngredientRecipePOJO ingr);
	
	public ArrayList<IngredientRecipePOJO> getAllIngredientRecipeByIdRecipe(String idRecipe);
	
	public boolean updateIngredientRecipe(String idRecipe, String ingredientName, double quantity);

}
