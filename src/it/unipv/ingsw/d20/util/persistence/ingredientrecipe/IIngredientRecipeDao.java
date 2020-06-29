package it.unipv.ingsw.d20.util.persistence.ingredientrecipe;

import java.util.ArrayList;

public interface IIngredientRecipeDao {
	
	/**
	 * Salva una lista di ingredienti.
	 * @param ingr
	 */
	public void addIngredientRecipe(ArrayList<IngredientRecipePOJO> ingr);
	
	/**
	 * Salva un solo ingrediente.
	 * @param ingr
	 */
	public void addIngredientRecipe(IngredientRecipePOJO ingr);
	
	/**
	 * Ottiene tutti gli ingredienti con l'idRecipe specificato come parametro. Quindi ottiene tutti gli ingredienti di una ricetta.
	 * @param idRecipe
	 * @return
	 */
	public ArrayList<IngredientRecipePOJO> getAllIngredientRecipeByIdRecipe(String idRecipe);
	
	/**
	 * Aggiorna l'ingrediente specificato come parametro, aggiornando la quantita'.
	 * @param idRecipe
	 * @param ingredientName
	 * @param quantity
	 * @return
	 */
	public boolean updateIngredientRecipe(String idRecipe, String ingredientName, double quantity);

}
