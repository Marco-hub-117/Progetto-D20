package it.unipv.ingsw.d20.persistence.BeverageDescription;

import java.util.ArrayList;

import it.unipv.ingsw.d20.persistence.ingredientRecipe.IngredientRecipePOJO;

public interface IBeverageDescriptionDao {
	
	public double getPriceByBevName(String bevName);
	
	public void addBeverageDescription(BeverageDescriptionPOJO bv);
	public void addBeverageDescription(BeverageDescriptionPOJO bv,ArrayList<IngredientRecipePOJO> ingr);

}
