package it.unipv.ingsw.d20.vendingmachine.model.exceptions;
/**
 * L'eccezione viene lanciata quando l'utente chiede una bevanda esaurita 
 */
@SuppressWarnings("serial")
public class InsufficientIngredientsException extends Exception {

	public InsufficientIngredientsException(String message) {
		super(message);
	}


}
