package it.unipv.ingsw.d20.vendingmachine.model.exceptions;
/**
 * L'eccezione viene lanciata quando l'utente digita il codice di una bevanda che non è nel catalogo
 */
@SuppressWarnings("serial")
public class NonExistentCodeException extends Exception {
	public NonExistentCodeException(String message) {
		super(message);
	}
}
