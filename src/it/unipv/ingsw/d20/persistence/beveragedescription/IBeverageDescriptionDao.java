package it.unipv.ingsw.d20.persistence.beveragedescription;

import java.util.ArrayList;

import it.unipv.ingsw.d20.persistence.ingredientrecipe.IngredientRecipePOJO;

public interface IBeverageDescriptionDao {
	
	public double getPriceByBevName(String bevName);
	
	public void addBeverageDescription(BeverageDescriptionPOJO bv);
	public void addBeverageDescription(BeverageDescriptionPOJO bv,ArrayList<IngredientRecipePOJO> ingr);

}
