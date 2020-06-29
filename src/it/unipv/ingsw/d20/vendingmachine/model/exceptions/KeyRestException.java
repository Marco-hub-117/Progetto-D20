package it.unipv.ingsw.d20.vendingmachine.model.exceptions;
/**
 * L'eccezione viene lanciata quando si chiede il resto mentre è inserita una chiavetta.
 * */
@SuppressWarnings("serial")
public class KeyRestException extends Exception {

	public KeyRestException(String message) {
		super(message);
	}

}
