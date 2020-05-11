package it.unipv.ingsw.d20.beverage.exception;

@SuppressWarnings("serial")
public class InsufficientIngredientsException extends Exception {
	
	public InsufficientIngredientsException() {
		super("Insufficient ingredients");
	}

}
