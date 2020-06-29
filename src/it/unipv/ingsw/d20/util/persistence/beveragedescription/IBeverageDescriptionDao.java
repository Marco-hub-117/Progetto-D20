package it.unipv.ingsw.d20.util.persistence.beveragedescription;

import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.IngredientRecipePOJO;

public interface IBeverageDescriptionDao {
	
	/**
	 * Ottiene il prezzo della bevanda specificata nel parametro BevName
	 * @param bevName
	 * @return
	 */
	public double getPriceByBevName(String bevName);
	
	/**
	 * Salva una BeverageDescription, non specificando gli ingredienti.
	 * @param bv
	 */
	public void addBeverageDescription(BeverageDescriptionPOJO bv);
	
	/**
	 * Salva una BeverageDescription, specificando anche tutti i suoi ingredienti.
	 * @param bv
	 * @param ingr
	 */
	public void addBeverageDescription(BeverageDescriptionPOJO bv,ArrayList<IngredientRecipePOJO> ingr);
	
	/**
	 * Ottiene una BeverageDescription, passando come parametro il nome della Bevanda.
	 * @param BevName
	 * @return
	 */
	public BeverageDescriptionPOJO getBeverageDescriptionByBevName(String BevName);
	
	/**
	 * Ottiene tutte le BeverageDescription salvate.
	 * @return
	 */
	public ArrayList<BeverageDescriptionPOJO> getAllBeverageDescriptions ();

}
