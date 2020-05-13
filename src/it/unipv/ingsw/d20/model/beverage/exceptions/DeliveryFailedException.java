package it.unipv.ingsw.d20.model.beverage.exception;

@SuppressWarnings("serial")
public class InsufficientIngredientsException extends Exception {
	
	public InsufficientIngredientsException() {
		super("Insufficient ingredients");
	}

}
