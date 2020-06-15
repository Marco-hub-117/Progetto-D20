package it.unipv.ingsw.d20.util.persistence.beveragedescription;

import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.IngredientRecipePOJO;

public interface IBeverageDescriptionDao {
	
	public double getPriceByBevName(String bevName);
	
	public void addBeverageDescription(BeverageDescriptionPOJO bv);
	
	public void addBeverageDescription(BeverageDescriptionPOJO bv,ArrayList<IngredientRecipePOJO> ingr);
	
	public BeverageDescriptionPOJO getBeverageDescriptionByBevName(String BevName);

}
