package it.unipv.ingsw.d20.util.persistence.ingredientrecipe;

import java.util.ArrayList;

public interface IIngredientRecipeDao {
	
	/**
	 * Salva una lista di ingredienti.
	 * @param ingr ingrediente
	 */
	public void addIngredientRecipe(ArrayList<IngredientRecipePOJO> ingr);
	
	/**
	 * Salva un solo ingrediente.
	 * @param ingr ingrediente
	 */
	public void addIngredientRecipe(IngredientRecipePOJO ingr);
	
	/**
	 * Ottiene tutti gli ingredienti con l'idRecipe specificato come parametro. Quindi ottiene tutti gli ingredienti di una ricetta.
	 * @param idRecipe id della ricetta
	 * @return ricetta completa
	 */
	public ArrayList<IngredientRecipePOJO> getAllIngredientRecipeByIdRecipe(String idRecipe);
	
	/**
	 * Aggiorna l'ingrediente specificato come parametro, aggiornando la quantita'.
	 * @param idRecipe id della ricetta
	 * @param ingredientName nome dell'ingrediente
	 * @param quantity quantità dell'ingrediente
	 * @return boolean esito dell'operazione
	 */
	public boolean updateIngredientRecipe(String idRecipe, String ingredientName, double quantity);

}
