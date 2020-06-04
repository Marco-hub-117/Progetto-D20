package it.unipv.ingsw.d20.persistence.ingredientRecipe;

import java.util.ArrayList;

public interface IingredientRecipeDao {
	
	public void addIngredientRecipe(ArrayList<IngredientRecipePOJO> ingr);
	
	public void addIngredientRecipe(IngredientRecipePOJO ingr);

}
