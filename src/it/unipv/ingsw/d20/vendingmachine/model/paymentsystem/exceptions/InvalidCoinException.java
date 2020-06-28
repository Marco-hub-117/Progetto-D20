package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions;

/**
 * Eccezione che viene lanciata quando la moneta inserita non 
 * è riconosciuta dalla macchinetta.
 *
 */
@SuppressWarnings("serial")
public class InvalidCoinException extends Exception {
	
	public InvalidCoinException(String message) {
		super(message);
	}

}
