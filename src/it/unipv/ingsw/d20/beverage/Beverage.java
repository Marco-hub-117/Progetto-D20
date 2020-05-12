package it.unipv.ingsw.d20.beverage;

import it.unipv.ingsw.d20.beverage.exception.InsufficientIngredientsException;

public class Beverage {
	
	private BeverageDescription beverageDescription;
	
	public Beverage(BeverageDescription b) throws InsufficientIngredientsException {
		this.beverageDescription=b;
		//INVECE DEL METODO hasBeenDelivered ABBIAMO PREFERITO USARE UN'ECCEZIONE, CHE VIENE GESTITA NELLA SALE, IN CASO NON FOSSE POSSIBILE EROGARE LA BEVANDA. 
		//L'ECCEZIONE DEVE ESSERE LANCIATA DAL COSTRUTTORE
	}
	
	public void makeNewBeverage(BeverageDescription b) {
		//da fare
	}

}
