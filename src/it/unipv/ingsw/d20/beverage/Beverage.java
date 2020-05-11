package it.unipv.ingsw.d20.beverage;

import it.unipv.ingsw.d20.beverage.exception.InsufficientIngredientsException;

public class Beverage {
	
	private BeverageDescription beverageDescription;
	
	public Beverage(BeverageDescription b) throws InsufficientIngredientsException {
		this.beverageDescription=b;
	}
	
	public void makeNewBeverage(BeverageDescription b) {
		//da fare
	}

}
