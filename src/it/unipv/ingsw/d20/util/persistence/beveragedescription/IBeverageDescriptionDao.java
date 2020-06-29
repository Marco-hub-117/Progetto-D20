package it.unipv.ingsw.d20.util.persistence.beveragedescription;

import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.IngredientRecipePOJO;

public interface IBeverageDescriptionDao {
	
	/**
	 * Ottiene il prezzo della bevanda specificata nel parametro BevName
	 * @param bevName nome della bevanda
	 * @return price prezzo
	 */
	public double getPriceByBevName(String bevName);
	
	/**
	 * Salva una BeverageDescription, non specificando gli ingredienti.
	 * @param bv POJO di BeverageDescription
	 */
	public void addBeverageDescription(BeverageDescriptionPOJO bv);
	
	/**
	 * Salva una BeverageDescription, specificando anche tutti i suoi ingredienti.
	 * @param bv POJO di BeverageDescription
	 * @param ingr ingrediente
	 */
	public void addBeverageDescription(BeverageDescriptionPOJO bv,ArrayList<IngredientRecipePOJO> ingr);
	
	/**
	 * Ottiene una BeverageDescription, passando come parametro il nome della Bevanda.
	 * @param BevName nome della bevanda
	 * @return bvDesc BeverageDescription
	 */
	public BeverageDescriptionPOJO getBeverageDescriptionByBevName(String BevName);
	
	/**
	 * Ottiene tutte le BeverageDescription salvate.
	 * @return bvDescList lista delle BeverageDescription
	 */
	public ArrayList<BeverageDescriptionPOJO> getAllBeverageDescriptions ();

}
